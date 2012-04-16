package users;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Users implements Serializable {
	private static final long serialVersionUID = 6360011364108881333L;
	private static List<User> usersList = new ArrayList<User>();
	public static String getUserRole(String name, String pass){
		for (User user : usersList) {
			if ((user.getName().equals(name)) && (user.getPass().equals(pass)))
				return user.getRole();
		}
		return null;
	}
	public static void addUser(String name, String pass, String role){
		usersList.add(new User(name, pass, role));
		commit();
	}
	public static boolean removeUser(String name){
		for (User user : usersList) {
			if (user.getName().equals(name)) {
				usersList.remove(user);
				return true;
			}
		}
		return false;
	}
	public static boolean commit(){
		try {
			FileOutputStream fos = new FileOutputStream("file.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(usersList);
			oos.flush();
			oos.close();
			System.out.println("Saved !");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("cannot save to file");
			return false;
		}
	return true;
	}
	
	public static boolean rollback() {
		try {
			FileInputStream fis = new FileInputStream("file.dat");
			ObjectInputStream oin = new ObjectInputStream(fis);
			usersList = (List<User>) oin.readObject();
		} catch (Exception e) {
			System.out.println("error");
			return false;
		}
	return true;
	}
	
	static {
		rollback();
	}
}

class User implements Serializable{
	private static final long serialVersionUID = 4325859235122150523L;
	private String name;
	private String role;
	private String pass;
	public User(String name, String pass, String role){
		this.setName(name);
		this.setPass(pass);
		this.setRole(role);
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	public String getPass() {
		return pass;
	}
	private void setName(String name) {
		this.name = name;
	}
	private void setRole(String role) {
		this.role = role;
	}
	private void setPass(String pass) {
		this.pass = pass;
	}
}
