package wyd.domain.dto

import groovyx.gaelyk.datastore.Entity
import groovyx.gaelyk.datastore.Key
import groovyx.gaelyk.datastore.Unindexed
import groovyx.gaelyk.datastore.Ignore
 
@Entity(unindexed=false)
public class User {

	@Key
	String id
	String password

	String name
	String emailId

	@Unindexed
	String timeZone

	Date createdTime
	Date updatedTime

	@Override
	public String toString() {
		return User.class.name + "[id = $id, password = $password, name = $name, eamilId = $emailId, timeZone = $timeZone, createdTime = $createdTime, updatedTime = $updatedTime]"
	}
}
