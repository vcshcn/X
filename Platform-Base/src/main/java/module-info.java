module net.xway.platform.base {
	
	requires java.logging;
	requires java.sql;
	requires org.mybatis;
	requires transitive org.mybatis.spring;
	requires spring.beans;
	requires spring.context;
	requires spring.tx;
	requires commons.dbcp2;
	requires commons.logging;

	exports net.xway.base;
	exports net.xway.base.database;
	exports net.xway.base.database.mybatis.dao;
	exports net.xway.base.service;
	exports net.xway.base.utils;
}
