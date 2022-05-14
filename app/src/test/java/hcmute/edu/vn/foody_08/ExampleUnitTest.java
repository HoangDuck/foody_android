package hcmute.edu.vn.foody_08;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        List<Integer> list=new ArrayList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        Gson gson=new Gson();
        String t=gson.toJson(list);
        System.out.println(t);
    }
}