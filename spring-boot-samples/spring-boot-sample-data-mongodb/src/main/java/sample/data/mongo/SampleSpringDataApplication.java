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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sample.data.transactional.Employee;
import sample.data.transactional.EmployeeRepository;

@ComponentScan(basePackages ={"sample.data"})
@EnableMongoRepositories(basePackageClasses=Customer.class)
@EnableJpaRepositories(basePackageClasses=Employee.class)
@SpringBootApplication
public class SampleSpringDataApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	@Autowired
	private OwnerRepository repo;

	@Autowired
	EmployeeRepository empRepository;
	@Override
	public void run(String... args) throws Exception {
		this.repository.deleteAll();
		//this.repo.deleteAll();
		empRepository.deleteAll();
		empRepository.save(new Employee("Hemanth", "Kumar"));
		System.out.println("Done");
		// save a couple of customers
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

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByAge(96));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : this.repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleSpringDataApplication.class, args);
	}

}
