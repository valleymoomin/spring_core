package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    //자기자신을 내부에 private으로 하나 가지고있는데 static으로 가지고있다.
    //class level에 올라가서 딱 1개 존재....
    //static 영역에 하나만 만들어져 올라간다.
    //자기 자신 객체 인스턴스 참조중


    //저 객체를 조회하기 위해
    public static SingletonService getInstance(){
        return instance;    //생성 이미 자바가 뜰때 생성된 애를 갖다 쓰는거임.
        //생성 1000 , 참조가져오기 1
    }

    private SingletonService(){

    } //private 생성자자


    public void  logic(){
        System.out.println("싱긅톤 객체 로직 호출");
    }
}
