import java.io.*;



public class IOStream implements AutoCloseable {

    public static void main(String[] args) {


        try {
            InputStream in = new FileInputStream( "hi.txt" );
            OutputStream out = new FileOutputStream( "reality.txt" );
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = in.read(buffer)) != -1){
                out.write( buffer, 0, length );
                System.out.println(length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException( e );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }

    }

    @Override
    public void close() throws Exception {

    }
}


