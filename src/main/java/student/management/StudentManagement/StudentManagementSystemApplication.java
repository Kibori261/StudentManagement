package student.management.StudentManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementSystemApplication {

	private Map<String, String> studentInfoMap = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@PostMapping("/register")
	public void registerStudentInfo(String name, String age) {
		studentInfoMap.put(name, age);
	}


	@GetMapping("/print")
	public String printAllStudentInfo() {

		int cnt = studentInfoMap.size();
		String students = "";
		if (cnt == 0) {
			return "no data";
		} else {
			for (Entry<String, String> entry : studentInfoMap.entrySet()) {
				String name = entry.getKey();
				String age = entry.getValue();
				students = students + "\n氏名：" + name + "　年齢：" + age;
			}
			return students;
		}
	}

	@DeleteMapping("/delete")
	public String deleteStudentInfo(String name) {
		if (studentInfoMap.containsKey(name)) {
			studentInfoMap.remove(name);
			return name + "is deleted.";
		} else {
			return name + " hasn't any value.";
		}
	}
	//`コマンドは、curl -X DELETE "http://localhost:8080/delete?name=苗字%20名前"　%20はスペースを表す

	@PutMapping("/update")
	public String updateStudentInfo(String name, String age) {
		if (studentInfoMap.containsKey(name)) {
			studentInfoMap.put(name, age);
			return name + "'s age is updated：" + age;
		} else {
			return name + " hasn't any value.";
		}
	}
	//コマンドはcurl -X PUT "http://localhost:8080/update?name=苗字%20名前&age=年齢"

}

