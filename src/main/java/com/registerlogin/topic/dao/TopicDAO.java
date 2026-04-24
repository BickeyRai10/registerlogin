package com.registerlogin.topic.dao;

import com.registerlogin.user.model.TopicModel;
import com.registerlogin.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class TopicDAO {
    public boolean isTopicExist(String topic_name){
        String sql="SELECT * FROM topic where name=?";
        try(Connection con= DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
        ){
            ps.setString(1,topic_name);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println("Error while searching topic "+ e.getMessage());
            return false;
        }
    }

    public boolean insertTopic(String topic_name){
        String sql="INSERT INTO topic(name,createdAt) VALUES(?,?)";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
        ){
            Timestamp now=new Timestamp(System.currentTimeMillis());
            ps.setString(1,topic_name);
            ps.setTimestamp(2,now);
            int row=ps.executeUpdate();
            return row>0;
        }catch (Exception e){
            System.out.println("Error while adding topic"+ e.getMessage());
            return false;
        }
    }

    public boolean updateTopic(String topic_name,int id){
        String sql="UPDATE topic SET name=?,updatedAt=? WHERE id=?";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
        ){
            Timestamp now=new Timestamp(System.currentTimeMillis());
            ps.setString(1,topic_name);
            ps.setTimestamp(2,now);
            ps.setInt(3,id);
            int row=ps.executeUpdate();
            return row>0;
        }catch (Exception e){
            System.out.println("Error while updating topic "+e.getMessage());
            return false;
        }
    }

    public boolean deleteTopic(int id){
        String sql="DELETE topic where id=?";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql)
        ){
            ps.setInt(1,id);
            int row=ps.executeUpdate();
            return row>0;
        } catch (Exception e) {
            System.out.println("Error while deleting user"+ e.getMessage());
            return false;
        }
    }

    public ArrayList<TopicModel> getAllTopics(){
        String sql="SELECT * FROM topic";
        try(Connection con=DBConnection.getConnection();
            Statement s=con.createStatement();
        ){
            ResultSet rs=s.executeQuery(sql);
            ArrayList<TopicModel> topics=new ArrayList<>();
            while(rs.next()){
                TopicModel topic=new TopicModel();
                int id=rs.getInt("id");
                String topic_name=rs.getString("name");
                Timestamp createdAt=rs.getTimestamp("createdAt");
                Timestamp updatedAt=rs.getTimestamp("updatedAt");
                topic.setId(id);
                topic.setName(topic_name);
                topic.setCreatedAt(createdAt);
                topic.setUpdatedAt(updatedAt);
                topics.add(topic);
            }
            return topics;

        }catch (Exception e){
            System.out.println("Error while fetching topics"+e.getMessage());
            return null;
        }
    }

    public TopicModel getSingleTopic(int id){
        String sql="Select * from topic where id=?";
        try(Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
        ){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                TopicModel topic=new TopicModel();
                topic.setId(rs.getInt(1));
                topic.setName(rs.getString(2));
                topic.setCreatedAt(rs.getTimestamp(3));
                topic.setUpdatedAt(rs.getTimestamp(4));
                return topic;
            }
            return null;
        } catch (Exception e) {
            System.out.println("error while searching topic"+e.getMessage());
            return null;
        }
    }


}