package reflect;

import java.lang.reflect.Constructor;

@AgeAnno("123")
public class AgeAnnoClass {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> myclass = AgeAnnoClass.class;
        AgeAnno myAnno = myclass.getAnnotation(AgeAnno.class);
        if(myAnno != null) {
            Object bean = myclass.newInstance();
            Constructor<?> constructor = myclass.getConstructor(Integer.class);
            System.out.println(myAnno.age());
        }
    }
}
