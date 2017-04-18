package examples;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZoneId;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static java.nio.file.StandardOpenOption.*;

public class IO {
	public static void main(String[] args) throws IOException {
		//SmallFiles();
		//TextFiles();
		//UnbufferedStreams();
		ChannelsandByteBuffers();
	}
    private static void SmallFiles() throws IOException {
		Path path = Paths.get("file.txt");
		Set<String> zoneIds = ZoneId.getAvailableZoneIds();
		Files.write(path, zoneIds, StandardOpenOption.CREATE);

		List<String> lines = Files.readAllLines(path);
		lines.forEach(System.out::println);
	}

	private static void TextFiles() {
		Path path = Paths.get("file.txt");
		Charset charset = Charset.forName("UTF-8");
		String s = "something";
		try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
			writer.write(s, 0, s.length());
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

	private static void UnbufferedStreams() {
		// Convert the string to a byte array.
		String s = "Hello World! ";
		byte data[] = s.getBytes();
		Path path = Paths.get("file.txt");

		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(path, CREATE))) {
			out.write(data, 0, data.length);
		} catch (IOException x) {
			System.err.println(x);
		}

		try (InputStream in = Files.newInputStream(path);
			 BufferedReader reader =
					 new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	private static void ChannelsandByteBuffers() throws IOException {
		Path path = Paths.get("file.txt");
		//stream I/O reads a character at a time, channel I/O reads a buffer at a time
		WritableByteChannel channel = Files.newByteChannel(path, EnumSet.of(CREATE, WRITE));
		String text = "abcde";
		//capacity and limit will be array.length; position zero; mark undefined
		ByteBuffer byteBuffer = ByteBuffer.wrap(text.getBytes());
		//Writes a sequence of bytes from buffer to channel
		channel.write(byteBuffer);

		SeekableByteChannel sbc = Files.newByteChannel(path);
		// Allocates a new byte buffer with position 0, limit=capacity,
		ByteBuffer buf = ByteBuffer.allocate(4);
		//Reads a sequence of bytes from this channel into the given buffer.
		int bytesRead = 0;
		while ((bytesRead = sbc.read(buf)) != -1) {
			buf.flip();//sets limit to position
			//whether there are any elements between the current position and the limit
			while (buf.hasRemaining()) {
				//get() reads the byte at this buffer's current position, and then increments the position.
				System.out.print((char) buf.get()); // read 1 byte at a time
			}
			buf.position(0);
		}
		channel.close();
	}
}