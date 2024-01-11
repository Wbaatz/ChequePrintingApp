<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import= "javax.servlet.http.HttpSession" %>
     <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        table {
            border-collapse: collapse;
            width: 50%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
 <h1>FeedBack Page</h1>
  <table>
        <tr>
            <th>Name</th>
            <th>Comments</th>
        </tr>

    <%
 
       ArrayList<String> Name =  (ArrayList<String>) session.getAttribute("key1");
       ArrayList<String> Comments =  (ArrayList<String>) session.getAttribute("key2");
 
    	if (Name != null && !Name.isEmpty()) {
            for (int i=0;i<Name.size();i++) {
            	
            String item1=Name.get(i);
            String item2=Comments.get(i);
    %>
         <tr>
            <td><%= item1 %></td>
            <td><%= item2 %></td>
        </tr>
   
    <%
            }
        } else {
    %>
            <p>No data received or values are null.</p>
    <%
        }
    %>
    
   
    </table>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</body>
</html>