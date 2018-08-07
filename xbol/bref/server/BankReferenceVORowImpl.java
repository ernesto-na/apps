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
/*     */   extends OAViewRowImpl {
/*     */
    public static final int NOMBREBANCO = 0;
    public static final int SUCURSAL = 1;
    public static final int REFERENCIA = 2;
    public static final int CUENTA = 3;
    public static final int UNIDADOPERATIVA = 4;
    public static final int NOMBRECOMPLETO = 5;
    public static final int CUENTATRANSFERENCIA = 6;
    public static final int OBSERVACIONES = 7;
    public static final int CLABE = 8;
    public static final int LASTUPDATEDATE = 9;
    public static final int LASTUPDATEDBY = 10;
    public static final int LASTUPDATELOGIN = 11;
    public static final int CREATIONDATE = 12;
    public static final int CREATEDBY = 13;
    public static final int NUMEROEMPLEADO = 14;
    public static final int BANK = 15;
    public static final int BANKCODE = 16;
    public static final int BRANCHCODE = 17;
    public static final int ACCOUNT = 18;
    public static final int IBAN = 19;
    public static final int SWIFT = 20;
    public static final int ABA = 21;
    public static final int CURRENCY = 22;
    public static final int IMPORTE = 23;

    /**This is the default constructor (do not remove)
     */
    public BankReferenceVORowImpl() {
    }
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
/*     */   public Number getBankCode()
/*     */   {
/* 197 */     return (Number)getAttributeInternal(16);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBankCode(Number value)
/*     */   {
/* 203 */     setAttributeInternal(16, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getBranchCode()
/*     */   {
/* 197 */     return (Number)getAttributeInternal(17);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBranchCode(Number value)
/*     */   {
/* 203 */     setAttributeInternal(17, value);
/*     */   }
/*     */   
/*     */ 
/*     */   
/*     */   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef)
/*     */     throws Exception
/*     */   {
/* 236 */
        /*     */switch (index) {
        case NOMBREBANCO:
            return getNombrebanco();
        case SUCURSAL:
            return getSucursal();
        case REFERENCIA:
            return getReferencia();
        case CUENTA:
            return getCuenta();
        case UNIDADOPERATIVA:
            return getUnidadOperativa();
        case NOMBRECOMPLETO:
            return getNombreCompleto();
        case CUENTATRANSFERENCIA:
            return getCuentaTransferencia();
        case OBSERVACIONES:
            return getObservaciones();
        case CLABE:
            return getClabe();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case NUMEROEMPLEADO:
            return getNumeroEmpleado();
        case BANK:
            return getBank();
        case BANKCODE:
            return getBankcode();
        case BRANCHCODE:
            return getBranchcode();
        case ACCOUNT:
            return getAccount();
        case IBAN:
            return getIban();
        case SWIFT:
            return getSwift();
        case ABA:
            return getAba();
        case CURRENCY:
            return getCurrency();
        case IMPORTE:
            return getImporte();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }
/*     */   
/*     */ 
/*     */   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef)
/*     */     throws Exception
/*     */   {
/* 255 */
        /*     */     
/* 267 */
        /*     */switch (index) {
        case CUENTATRANSFERENCIA:
            setCuentaTransferencia((String)value);
            return;
        case CLABE:
            setClabe((String)value);
            return;
        case IMPORTE:
            setImporte((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
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
/* 291 */     return (Number)getAttributeInternal(23);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setImporte(Number value)
/*     */   {
/* 297 */     setAttributeInternal(23, value);
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
/*     */

    /**Gets the attribute value for the calculated attribute Bank
     */
    public String getBank() {
        return (String) getAttributeInternal(BANK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Bank
     */
    public void setBank(String value) {
        setAttributeInternal(BANK, value);
    }

    /**Gets the attribute value for the calculated attribute Bankcode
     */
    public String getBankcode() {
        return (String) getAttributeInternal(BANKCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Bankcode
     */
    public void setBankcode(String value) {
        setAttributeInternal(BANKCODE, value);
    }

    /**Gets the attribute value for the calculated attribute Branchcode
     */
    public String getBranchcode() {
        return (String) getAttributeInternal(BRANCHCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Branchcode
     */
    public void setBranchcode(String value) {
        setAttributeInternal(BRANCHCODE, value);
    }

    /**Gets the attribute value for the calculated attribute Account
     */
    public String getAccount() {
        return (String) getAttributeInternal(ACCOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Account
     */
    public void setAccount(String value) {
        setAttributeInternal(ACCOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute Iban
     */
    public String getIban() {
        return (String) getAttributeInternal(IBAN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Iban
     */
    public void setIban(String value) {
        setAttributeInternal(IBAN, value);
    }

    /**Gets the attribute value for the calculated attribute Swift
     */
    public String getSwift() {
        return (String) getAttributeInternal(SWIFT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Swift
     */
    public void setSwift(String value) {
        setAttributeInternal(SWIFT, value);
    }

    /**Gets the attribute value for the calculated attribute Aba
     */
    public String getAba() {
        return (String) getAttributeInternal(ABA);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Aba
     */
    public void setAba(String value) {
        setAttributeInternal(ABA, value);
    }

    /**Gets the attribute value for the calculated attribute Currency
     */
    public String getCurrency() {
        return (String) getAttributeInternal(CURRENCY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Currency
     */
    public void setCurrency(String value) {
        setAttributeInternal(CURRENCY, value);
    }
}


/* Location:              C:\Users\GHCM-T430-06_2\Documents\Aeromexico\iExpenses\Fuentes\Link_referencia\bref_01_06_18.zip!\bref\server\BankReferenceVORowImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */