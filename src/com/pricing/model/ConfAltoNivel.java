package com.pricing.model;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;

public class ConfAltoNivel {
		//========atributos=====
		private int codAltoNivel;
		private int nperfilCod;
		private String cnombreEntidad;
		private boolean bEstado;
		private boolean estadoConYourself;
		private boolean estadoSinYourself;
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
		public boolean isEstadoConYourself() {
			return estadoConYourself;
		}
		public void setEstadoConYourself(boolean estadoConYourself) {
			this.estadoConYourself = estadoConYourself;
		}
		public boolean isEstadoSinYourself() {
			return estadoSinYourself;
		}
		public void setEstadoSinYourself(boolean estadoSinYourself) {
			this.estadoSinYourself = estadoSinYourself;
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
				this.setEstadoConYourself(true);
				this.setEstadoSinYourself(false);
			}else{
				this.setEstadoSinYourself(true);
				this.setEstadoConYourself(false);
			}
		}
		
}
