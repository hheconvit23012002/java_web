package com.example.spring.jdbc.book;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	private static StudentService bs = new StudentService();
	public static List<Integer> getAllId() throws IOException{		
		Statement st = null;
		ResultSet ids = null;
		List<Integer> listId = new ArrayList<>();
		try {
          st = (Statement) bs.connectDB().createStatement();
          ids = st.executeQuery("select id from Student");            		          
          while(ids.next()) {
          	listId.add(ids.getInt("id"));
          }
		}catch(Exception e) {
			e.getStackTrace();
		}
		return listId;
	}
	public static List<Student> getAll() throws IOException{
		Statement statement = null; 
        ResultSet resultSet = null; 
        List<Student> students = new ArrayList<Student>();
        List<String> temp = new ArrayList<String>();
        try {         
            statement = (Statement) bs.connectDB().createStatement();
            resultSet = statement.executeQuery("select * from Student");
            while(resultSet.next()) {
                int id = resultSet.getInt("id"); 
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String major = resultSet.getString("major");
                int vaccinated = resultSet.getInt("vaccinated");
                students.add(new Student(id,name,dob,major,vaccinated == 0 ? false : true));
            }
            for(Student a: students) {
            	temp.add(a.getId() + a.getName());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
	}
	public static Student get(String id) {
		PreparedStatement ps = null;
		ResultSet result = null;
		Student student = new Student();
		try {
			ps = bs.connectDB().prepareStatement("select * from Student where id = ?");
            ps.setInt(1, Integer.valueOf(id));
            result = ps.executeQuery();
            while(result.next()) {
            	student.setId(result.getInt("id"));
            	student.setName(result.getString("name"));
            	student.setDob(result.getString("dob"));
            	student.setMajor(result.getString("major"));
            	student.setVaccinated(result.getInt("vaccinated") != 0 ? true : false);
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	public static int save(Student student) throws IOException {
		PreparedStatement ps = null;		
		int result = 0;
		
		try {  
            
            ps = bs.connectDB().prepareStatement("set identity_insert Student on\r\n" + "insert into Student(id,name,dob,major,vaccinated) values(?,?,?,?,?)");             
            ps.setInt(1,Integer.valueOf(student.getId()));
            ps.setString(2,student.getName());
            ps.setString(3, student.getDob());
            ps.setString(4, student.getMajor());
            ps.setInt(5, student.getVaccinated()?1:0);

            result = ps.executeUpdate();
            ps.close();

            return 200;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}
	
	public static int update(Student student) throws IOException {
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			
			ps = bs.connectDB().prepareStatement("update Student set name=?, dob=?, major=?,vaccinated=? where id=?");
     
            ps.setString(1,student.getName());
            ps.setString(2, student.getDob());
            ps.setString(3, student.getMajor());
            ps.setInt(4, student.getVaccinated()?1:0);
            ps.setInt(5, Integer.valueOf(student.getId()));
            result = ps.executeUpdate();
            ps.close();
            
            return 200;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 500;
	}
	public static int delete(String id) {
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = bs.connectDB().prepareStatement("DELETE FROM Student WHERE id=?;");
            
            ps.setInt(1,Integer.valueOf(id));
            result = ps.executeUpdate();
            ps.close();
            
            return 200;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 500;
	}
}
