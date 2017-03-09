package com.pricing.model;

import java.util.ArrayList;

public class CCodigoPostal {
	private int codPostal;
	private String departamento;
	/**GETTER AND SETTER**/
	public int getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	/**CONSTRUCTORES**/
	public CCodigoPostal() {
		// TODO Auto-generated constructor stub
	}
	public CCodigoPostal(int codPostal, String departamento) {
		this.codPostal = codPostal;
		this.departamento = departamento;
	}
	/**METODOS**/
	public ArrayList<CCodigoPostal> listaCodigosPostales()
	{
		ArrayList<CCodigoPostal> lista=new ArrayList<CCodigoPostal>();
		lista.add(new CCodigoPostal(41,"AMAZONAS"));
		lista.add(new CCodigoPostal(43,"ANCASH"));
		lista.add(new CCodigoPostal(83,"APURIMAC"));
		lista.add(new CCodigoPostal(54,"AREQUIPA"));
		lista.add(new CCodigoPostal(66,"AYACUCHO"));
		lista.add(new CCodigoPostal(76,"CAJAMARCA"));
		lista.add(new CCodigoPostal(84,"CUSCO"));
		lista.add(new CCodigoPostal(67,"HUANCAVELICA"));
		lista.add(new CCodigoPostal(62,"HUANUCO"));
		lista.add(new CCodigoPostal(56,"ICA"));
		lista.add(new CCodigoPostal(64,"JUNIN"));
		lista.add(new CCodigoPostal(44,"LA LIBERTAD"));
		lista.add(new CCodigoPostal(74,"LAMBAYEQUE"));
		lista.add(new CCodigoPostal(1,"LIMA"));
		lista.add(new CCodigoPostal(65,"LORETO"));
		lista.add(new CCodigoPostal(82,"MADRE DE DIOS"));
		lista.add(new CCodigoPostal(53,"MOQUEGUA"));
		lista.add(new CCodigoPostal(63,"PASCO"));
		lista.add(new CCodigoPostal(73,"PIURA"));
		lista.add(new CCodigoPostal(51,"PUNO"));
		lista.add(new CCodigoPostal(42,"SAN MARTIN"));
		lista.add(new CCodigoPostal(52,"TACNA"));
		lista.add(new CCodigoPostal(72,"TUMBES"));
		lista.add(new CCodigoPostal(61,"UCAYALI"));
		return lista;
	}
}
