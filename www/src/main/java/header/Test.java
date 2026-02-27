package header;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) throws JsonProcessingException {
		JSONObject jobj = new JSONObject();
		jobj.append("name", "홍길동");
		jobj.append("age", "20");
		System.out.println(jobj);
		System.out.println(jobj.toString());
		System.out.println(jobj.get("name"));
		System.out.println(jobj.get("age"));
		System.out.println(jobj.get("name"));
		//String s= (String) jobj.get("name");
		JSONArray arr = jobj.getJSONArray("name");
		String s = arr.getString(0);
		System.out.println("s:"+s);
		System.out.println(arr.get(0));
		
		// 문자열을 jobj
		String sobj = "{\"id\":\"100\",\"name\":\"홍길동\",\"age\":\"20\"}";
		jobj = new JSONObject(sobj);
		System.out.println(jobj);
		System.out.println(jobj.toString());
		System.out.println(jobj.get("name"));
		System.out.println(jobj.get("age"));
		
		// 객체를 jsonobject로 변환
		Student stu = new Student("100", "김개똥", "25");
		JSONObject styobj = new JSONObject(stu);
		System.out.println("styobj:"+styobj);
		
		// google json과 bind를 이용
		Gson gson = new Gson();
		String stuJson = gson.toJson(stu);
		
		// 결과 출력
		System.out.println("stuJson: "+ stuJson);
		System.out.println(new JSONObject(stuJson));
		
		ObjectMapper objectMapper = new ObjectMapper();
		String stuJson2 = objectMapper.writeValueAsString(stu);
		
		// 결과 출력
	}

}

class Student{
	String id;
	String address;
	String age;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String id, String address, String age) {
		super();
		this.id = id;
		this.address = address;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", age=" + age + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
	
}