/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.data.mongo;

import org.hibernate.SessionBuilder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.util.SpaceRegistryUtil;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sample.data.transactional.Employee;
import sample.data.transactional.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ComponentScan(basePackages ={"sample.data"})
@EnableMongoRepositories(basePackageClasses=Customer.class)
@EnableJpaRepositories(basePackageClasses=Employee.class)
@SpringBootApplication
public class SampleSpringDataApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private OwnerRepository repo;

	private SpaceRegistryUtil spaceRepo= new SpaceRegistryUtil();
	@Autowired
	EmployeeRepository empRepository;
	@Override
	public void run(String... args) throws Exception {

//		EntityManager entityManager = Persistence.createEntityManagerFactory("employeeEntity").createEntityManager();
//		entityManager.close();
		//spaceRepo.registerTenant("tenantb");
		persistSampleTranscationalData();

	}

	private void persistSampleTranscationalData(){
		this.repository.deleteAll();
		Employee emp = new Employee("Hemanth", "Kumar","tenant1");
		Employee emp2 = new Employee("Admin", "DV","tenantb");

//		EntityManager entityManager = Persistence.createEntityManagerFactory("employeeEntity").createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(emp2);
//		entityManager.getTransaction().commit();
//		entityManager.flush();

		//empRepository.deleteAll();
		//Persistence.generateSchema();
		//this.repo.deleteAll();

		//empRepository.save(emp);
		empRepository.save(emp2);
		System.out.println("Done");
	}

	private void persistSampleDocumentData(){
		this.repository.save(new Customer("Hemanth", "Kumar",96, "system"));

		//this.repository.save(new Customer("Bob", "Smith",26,"tenant1"));
		//this.repo.save(new Owner("DeltaVerge", "BPS",2));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : this.repository.findAll()) {
			System.out.println(customer);
		}
//		for (Owner owner : this.repo.findAll()) {
//			System.out.println(owner);
//		}
		System.out.println();

	}
	public static void main(String[] args) {
		SpringApplication.run(SampleSpringDataApplication.class, args);
	}

}
