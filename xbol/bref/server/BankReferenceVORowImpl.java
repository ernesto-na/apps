/*     */ package xxgam.oracle.apps.xbol.bref.server;
/*     */ 
/*     */ import oracle.apps.fnd.framework.server.OAViewRowImpl;
/*     */ import oracle.jbo.domain.Date;
/*     */ import oracle.jbo.domain.Number;
/*     */ import oracle.jbo.server.AttributeDefImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BankReferenceVORowImpl
/*     */   extends OAViewRowImpl
/*     */ {
/*     */   public static final int NOMBREBANCO = 0;
/*     */   public static final int SUCURSAL = 1;
/*     */   public static final int REFERENCIA = 2;
/*     */   public static final int CUENTA = 3;
/*     */   public static final int UNIDADOPERATIVA = 4;
/*     */   public static final int NOMBRECOMPLETO = 5;
/*     */   public static final int CUENTATRANSFERENCIA = 6;
/*     */   public static final int OBSERVACIONES = 7;
/*     */   public static final int CLABE = 8;
/*     */   public static final int LASTUPDATEDATE = 9;
/*     */   public static final int LASTUPDATEDBY = 10;
/*     */   public static final int LASTUPDATELOGIN = 11;
/*     */   public static final int CREATIONDATE = 12;
/*     */   public static final int CREATEDBY = 13;
/*     */   public static final int NUMEROEMPLEADO = 14;
/*     */   public static final int IMPORTE = 15;
/*     */   
/*     */   public String getNombrebanco()
/*     */   {
/*  43 */     return (String)getAttributeInternal(0);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setNombrebanco(String value)
/*     */   {
/*  49 */     setAttributeInternal(0, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getSucursal()
/*     */   {
/*  57 */     return (String)getAttributeInternal(1);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSucursal(String value)
/*     */   {
/*  63 */     setAttributeInternal(1, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getReferencia()
/*     */   {
/*  71 */     return (String)getAttributeInternal(2);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setReferencia(String value)
/*     */   {
/*  77 */     setAttributeInternal(2, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCuenta()
/*     */   {
/*  85 */     return (String)getAttributeInternal(3);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCuenta(String value)
/*     */   {
/*  91 */     setAttributeInternal(3, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUnidadOperativa()
/*     */   {
/*  99 */     return (String)getAttributeInternal(4);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setUnidadOperativa(String value)
/*     */   {
/* 105 */     setAttributeInternal(4, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNombreCompleto()
/*     */   {
/* 113 */     return (String)getAttributeInternal(5);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setNombreCompleto(String value)
/*     */   {
/* 119 */     setAttributeInternal(5, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getObservaciones()
/*     */   {
/* 127 */     return (String)getAttributeInternal(7);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setObservaciones(String value)
/*     */   {
/* 133 */     setAttributeInternal(7, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getLastUpdateDate()
/*     */   {
/* 141 */     return (Date)getAttributeInternal(9);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLastUpdateDate(Date value)
/*     */   {
/* 147 */     setAttributeInternal(9, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getLastUpdatedBy()
/*     */   {
/* 155 */     return (Number)getAttributeInternal(10);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLastUpdatedBy(Number value)
/*     */   {
/* 161 */     setAttributeInternal(10, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getLastUpdateLogin()
/*     */   {
/* 169 */     return (Number)getAttributeInternal(11);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLastUpdateLogin(Number value)
/*     */   {
/* 175 */     setAttributeInternal(11, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Date getCreationDate()
/*     */   {
/* 183 */     return (Date)getAttributeInternal(12);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCreationDate(Date value)
/*     */   {
/* 189 */     setAttributeInternal(12, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getCreatedBy()
/*     */   {
/* 197 */     return (Number)getAttributeInternal(13);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCreatedBy(Number value)
/*     */   {
/* 203 */     setAttributeInternal(13, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
/*     */     throws Exception
/*     */   {
/* 211 */     switch (index)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     case 0: 
/* 248 */       return getNombrebanco(); case 1:  return getSucursal(); case 2:  return getReferencia(); case 3:  return getCuenta(); case 4:  return getUnidadOperativa(); case 5:  return getNombreCompleto(); case 6:  return getCuentaTransferencia(); case 7:  return getObservaciones(); case 8:  return getClabe(); case 9:  return getLastUpdateDate(); case 10:  return getLastUpdatedBy(); case 11:  return getLastUpdateLogin(); case 12:  return getCreationDate(); case 13:  return getCreatedBy(); case 14:  return getNumeroEmpleado(); case 15:  return getImporte(); } return super.getAttrInvokeAccessor(index, attrDef);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef)
/*     */     throws Exception
/*     */   {
/* 255 */     switch (index)
/*     */     {
/*     */     case 6: 
/* 258 */       setCuentaTransferencia((String)value); return;
/*     */     
/*     */     case 8: 
/* 261 */       setClabe((String)value); return;
/*     */     
/*     */     case 15: 
/* 264 */       setImporte((Number)value); return;
/*     */     }
/*     */     
/* 267 */     super.setAttrInvokeAccessor(index, value, attrDef);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getClabe()
/*     */   {
/* 277 */     return (String)getAttributeInternal(8);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setClabe(String value)
/*     */   {
/* 283 */     setAttributeInternal(8, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getImporte()
/*     */   {
/* 291 */     return (Number)getAttributeInternal(15);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setImporte(Number value)
/*     */   {
/* 297 */     setAttributeInternal(15, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getNumeroEmpleado()
/*     */   {
/* 306 */     return (String)getAttributeInternal(14);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setNumeroEmpleado(String value)
/*     */   {
/* 312 */     setAttributeInternal(14, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCuentaTransferencia()
/*     */   {
/* 320 */     return (String)getAttributeInternal(6);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCuentaTransferencia(String value)
/*     */   {
/* 326 */     setAttributeInternal(6, value);
/*     */   }
/*     */ }


/* Location:              C:\Users\GHCM-T430-06_2\Documents\Aeromexico\iExpenses\Fuentes\Link_referencia\bref_01_06_18.zip!\bref\server\BankReferenceVORowImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */