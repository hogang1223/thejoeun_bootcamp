<%-- <%@page import="java.util.GregorianCalendar"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="today.jsp" %>
		현재 시각은 <%=String.format("%TH시 %TM분 %TS초", now, now, now) %>입니다.
