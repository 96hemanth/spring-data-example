/*
 * Copyright 2012-2013 the original author or authors.
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

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Customer implements Serializable {

	@Id
	private String id;

	
	private String firstName;
	private String lastName;
	private int age;
	private String polyglotSubdomainName = "ISAM";
	private String polyglotStructureName = "users";
	private String tenantId;
	public Customer() {
	}

	public Customer(String firstName, String lastName, int age, String tenantId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%s, firstName='%s', lastName='%s', age='%s']", id,
				firstName, lastName,age);
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getPolyglotSubdomainName() {
		return polyglotSubdomainName;
	}

	public String getPolyglotStructureName() {
		return polyglotStructureName;
	}

	public String getTenantId() {
		return tenantId;
	}
}
