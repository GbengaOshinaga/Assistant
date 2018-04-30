package processes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;

import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;

import mock.JsonMock;

class WikiIntroProcessTest {


  @Test
  void testGetTitle() {
    WikiIntroProcess process = WikiIntroProcess.getInstance();
    @SuppressWarnings("rawtypes")
    Class[] parameters = {String.class};
    try {
      Method method = process.getClass().getDeclaredMethod("getTitle", parameters);
      method.setAccessible(true);
      assertEquals("John%20Rockefeller", method.invoke(process, "wiki john rockefeller"));
      assertEquals("John%20Rockefeller", method.invoke(process, "john rockefeller wiki"));
    } catch (NoSuchMethodException | 
        SecurityException | 
        IllegalAccessException |
        IllegalArgumentException | 
        InvocationTargetException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  void testExecute() {
    WikiIntroProcess process = WikiIntroProcess.getInstance();
    process.setText("wiki juice");
    String url = "https://en.wikipedia.org/w/api.php?format=json&action=query" + 
        "&prop=extracts&exintro=&explaintext=&titles=Juice";
    try {
      URLConnection conn = new URL(url).openConnection();
      PowerMockito.whenNew(URLConnection.class).withAnyArguments().thenReturn(conn);
      URLConnection connMock = PowerMockito.mock(URLConnection.class);
      File file = new File("./src/test/java/mock/wiki.json");
      InputStream stream = new FileInputStream(file);
      PowerMockito.when(connMock.getInputStream()).thenReturn(stream);
    } catch (Exception e) {
      e.printStackTrace();
    }

    assertEquals("\""+ JsonMock.wikiResponse +"\"", process.execute());
  }

}
