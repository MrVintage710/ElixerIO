package com.zenith.elixer.specification;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class ElixerPNG extends ElixerFileSpecification implements IImage {

    private int width, height;
    private byte bitDepth, compressionMethod, filterMethod, interlaceMethod;
    ColorType colorType = ColorType.UNKNOWN;
    private Color[][] imageData;

    private String currentChunkType;

    private enum ColorType {
        GRAYSCALE(1),
        TRUECOLOR(3),
        INDEXED(1),
        GRAYSCALE_AND_ALPHA(2),
        TRUECOLOR_AND_ALPHA(4),
        UNKNOWN(0);

        private int numChannels;

        ColorType(int numChannels) {
            this.numChannels = numChannels;
        }

        public int getNumChannels() {
            return numChannels;
        }
    }

    public ElixerPNG(Path path) {
        super(path);
    }

    @Override
    protected void encode(ElixerBuffer buffer) {
        try {
            currentChunkType = "";
            buffer.read(8);
            while (!currentChunkType.equals("IEND")) {
                readChunk(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
    }

    private void readChunk(ElixerBuffer buffer) throws IOException, DataFormatException {
        int length = buffer.readInt();
        currentChunkType = buffer.readString(4);
        System.out.println(currentChunkType + " | " + length);
        switch (currentChunkType) {
            case "IHDR":
                readIHDR(buffer, length);
                break;
            case "IDAT":
                readIDAT(buffer, length);
                break;
            default:
                skipChunk(buffer, length);
                break;
        }
        byte[] crc = buffer.read(4);
    }

    private void readIHDR(ElixerBuffer buffer, int length) throws IOException {
        this.width = buffer.readInt();
        this.height = buffer.readInt();
        this.bitDepth = buffer.read();
        int colorType = buffer.read();
        this.compressionMethod = buffer.read();
        this.filterMethod = buffer.read();
        this.interlaceMethod = buffer.read();

        switch (colorType) {
            case 0:
                this.colorType = ColorType.GRAYSCALE;
                break;
            case 2:
                this.colorType = ColorType.TRUECOLOR;
                break;
            case 3:
                this.colorType = ColorType.INDEXED;
                break;
            case 4:
                this.colorType = ColorType.GRAYSCALE_AND_ALPHA;
                break;
            case 6:
                this.colorType = ColorType.TRUECOLOR_AND_ALPHA;
                break;
        }

        imageData = new Color[height][width];
    }

    private void readIDAT(ElixerBuffer buffer, int length) throws IOException, DataFormatException {
        byte[] data = buffer.read(length);
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        int bytesPerRow = 0;
        if(bitDepth == 1) {
            bytesPerRow = colorType.getNumChannels() * (width/8);
            if((width % 8) != 0) {
                bytesPerRow++;
            }
        } else if(bitDepth == 2) {
            bytesPerRow = colorType.getNumChannels() * (width/4);
            if((width % 4) != 0) {
                bytesPerRow++;
            }
        } else if(bitDepth == 4) {
            bytesPerRow = colorType.getNumChannels() * (width/2);
            if((width % 2) != 0) {
                bytesPerRow++;
            }
        } else if(bitDepth == 8) {
            bytesPerRow = colorType.getNumChannels() * width;
        } else if(bitDepth == 16) {
            bytesPerRow = colorType.getNumChannels() * (width*2);
        }

        byte[] currentRow = new byte[bytesPerRow];
        byte[] currentBytes;
        for (int y = 0; y < height; y++) {
            inflater.inflate(new byte[1]);
            inflater.inflate(currentRow);
            for (int x = 0; x < bytesPerRow; x += colorType.getNumChannels()) {
                currentBytes = new byte[colorType.getNumChannels()];
                for(int select = 0; select < colorType.getNumChannels(); select++) {
                    currentBytes[select] = currentRow[x+select];
                }
                readGrayScale(currentBytes, x, y);
            }
        }
    }

    private void skipChunk(ElixerBuffer buffer, int length) throws IOException {
        buffer.read(length);
    }

    private void readGrayScale(byte[] colorData, int x, int y){
        switch (bitDepth) {
            case 1:
                byte current = colorData[0];
                for (int bit = 0; bit < 8; bit++) {
                    if(((current << bit) & 0x80) == 0x80) {
                        setColor(x*8 + bit, y, 255, 255, 255);
                    } else {
                        setColor(x*8 + bit, y, 0, 0, 0);
                    }
                }
                break;
            case 2:
                for (int bit = 0; bit < 4; bit++) {

                }
        }
    }

    public IImage setColor(int x, int y, int r, int g, int b) {
        if(x <= width && y <= height)
            imageData[y][x] = new Color(r,g,b);

        return this;
    }
}
