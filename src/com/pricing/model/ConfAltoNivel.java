package com.pricing.model;


public class ConfAltoNivel {
		//========atributos=====
		private int codAltoNivel;
		private int nperfilCod;
		private String cnombreEntidad;
		private boolean bEstado;
		private boolean estadoConEntidad;
		private boolean estadoSinEntidad;
		//=====getter an setter====
		
		public int getCodAltoNivel() {
			return codAltoNivel;
		}
		public boolean isbEstado() {
			return bEstado;
		}
		public void setbEstado(boolean bEstado) {
			this.bEstado = bEstado;
		}
		public void setCodAltoNivel(int codAltoNivel) {
			this.codAltoNivel = codAltoNivel;
		}
		public int getNperfilCod() {
			return nperfilCod;
		}
		public void setNperfilCod(int nperfilCod) {
			this.nperfilCod = nperfilCod;
		}
		public String getCnombreEntidad() {
			return cnombreEntidad;
		}
		public void setCnombreEntidad(String cnombreEntidad) {
			this.cnombreEntidad = cnombreEntidad;
		}
		
		public boolean isEstadoConEntidad() {
			return estadoConEntidad;
		}
		public void setEstadoConEntidad(boolean estadoConEntidad) {
			this.estadoConEntidad = estadoConEntidad;
		}
		public boolean isEstadoSinEntidad() {
			return estadoSinEntidad;
		}
		public void setEstadoSinEntidad(boolean estadoSinEntidad) {
			this.estadoSinEntidad = estadoSinEntidad;
		}
		//==========constructor====
		public ConfAltoNivel(){
			super();
		}
		public ConfAltoNivel(int codAltoNivel, int nperfilCod, String cnombreEntidad,boolean bEstado) {
			this.codAltoNivel = codAltoNivel;
			this.nperfilCod = nperfilCod;
			this.cnombreEntidad = cnombreEntidad;
			this.bEstado=bEstado;
			if(this.bEstado){
				this.setEstadoConEntidad(true);
				this.setEstadoSinEntidad(false);
			}else{
				this.setEstadoConEntidad(false);
				this.setEstadoSinEntidad(true);
			}
		}
		
}
