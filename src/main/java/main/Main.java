package main;

import modelo.Agenda;
import modelo.SeedData;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.mysql.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;


public class Main 
{
	public static void main(String[] args) 
	{
		DAOAbstractFactory daoAbstractFactory = new DAOSQLFactory();
		SeedData.initialize(daoAbstractFactory);
		Vista vista = new Vista();
		Agenda modelo = new Agenda(daoAbstractFactory);
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();
	}
}
