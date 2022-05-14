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
        Gson gson=new Gson();
        String t=gson.toJson(list);
        Integer[] integerList=gson.fromJson("[1,2,3,4]",Integer[].class);
        System.out.println(integerList[3]);
    }
}