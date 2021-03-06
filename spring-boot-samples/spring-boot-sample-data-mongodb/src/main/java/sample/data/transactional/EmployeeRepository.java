package sample.data.transactional;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByLastName(String lastName);
}