package fanjh.mine.studybutterknife;

import android.support.annotation.ArrayRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;

import mine.fanjh.Test;
import mine.fanjh.TestField;

@Deprecated
public class MainActivity extends FragmentActivity {
    public static final int FINAL_VALUE = 1;
    public static Integer SINT = Integer.valueOf(2);
    public Integer mInt = Integer.valueOf(3);
    @TestField
    static View mView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(200,200);
        setContentView(R.layout.activity_main);
        Class cls = this.getClass();
        //获取作用在当前类上面的注解
        //Annotation []annotations = cls.getDeclaredAnnotations();
        Method []methods = cls.getDeclaredMethods();
        for(Method method:methods){
            method.setAccessible(true);
            //获取指定注解元素
            Demo demo = method.getAnnotation(Demo.class);
            if(null != demo){
                Log.i("tag2",demo.id()+":"+demo.text());
            }
            //获取所有方法上面的注解
            /*Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation:annotations){
                Log.i("tag2",annotation.annotationType()+":");
            }*/
        }
    }

    @Demo(id = 1,text = "first"+FINAL_VALUE)
    @Deprecated
    private void print1(){}
    @Demo
    private void print2(){}
    @Demo
    @Test
    private static final void print3(){}
    @ArrayRes
    private void print4(){}
    @ArrayRes
    private void print5(){}

}
