package com.compilerdart;
public class Sintatico {

    static Token token;
    static Scanner scanner = new Scanner("C:\\Users\\Rafael\\Documents\\Faculdade\\TERCEIRO SEMESTRE\\LINGUAGENS FORMAIS\\TrabalhoFinalCompilador\\projeto\\demo\\src\\main\\java\\com\\compilerdart\\input.txt");
    static int lastlastType;

    public void S() throws Exception {
        boolean continuar = true;
        do {
            token = scanner.nextToken();
            switch (token.getType()) {
                case Token.TK_VARTYPE:
                    DV();
                    lastlastType = token.getLastVarType();
                break;
                case Token.TK_FUCNTION:
                    F();
                break;
                case Token.TK_SFUCNTION:
                    SFR();
                break;
                case Token.VAR_BOOL:
                    A();
                    lastlastType = Token.TK_BOOL;
                break;
                case Token.VAR_DOUBLE:
                    A();
                    lastlastType = Token.TK_DOUBLE;
                break;
                case Token.VAR_INT:
                    A();
                    lastlastType = Token.TK_INT;
                break;
                case Token.VAR_STRING:
                    A();
                    lastlastType = Token.TK_STRING;
                break;
                case Token.TK_IF:
                    C();
                break;
                case Token.R_CBRACKET:
                    continuar = false;
                break;
                default:
                    break;
            } 
        }while (continuar);
        
    }

    public void DV() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.TK_VARNAME) {
            FD();
        }else{ throw new Exception("Exception message");}
    }

    public void FD() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.TK_ENDLINE) {
            
        } else if ( token.getType() == Token.TK_ATRB){
            A();
            token = scanner.nextToken();
            if (token.getType() == Token.TK_ENDLINE) {
                
            } else if ( token.getType() == Token.TK_ATRB){
            } else { throw new Exception("Exception message");}
        }
    }

    public void A() throws Exception {
        token = scanner.nextToken();
        switch (lastlastType) {
            case Token.TK_BOOL:
                if (token.getType() == Token.TK_BOOL || token.getType() == Token.VAR_BOOL) {
                    RTB();
                } else { throw new Exception("Exception message");}
                break;
            case Token.TK_DOUBLE:
                if (token.getType() == Token.TK_DOUBLE || token.getType() == Token.VAR_DOUBLE) {
                    RTD();
                } else { throw new Exception("Exception message");}
                break;
            case Token.TK_INT:
                if (token.getType() == Token.TK_INT || token.getType() == Token.VAR_INT) {
                    RTI();
                } else { throw new Exception("Exception message");}
                break;
            case Token.TK_STRING:
                if (token.getType() == Token.TK_STRING || token.getType() == Token.VAR_STRING || token.getType() == Token.TK_SFUCNTION) {
                    
                } else { throw new Exception("Exception message");}
                break;

            default:
                break;
        }
    }

    public void F() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.L_BRACKET) {
            token = scanner.nextToken();
            A();
                token = scanner.nextToken();
                if (token.getType() == Token.R_BRACKET) {
                    token = scanner.nextToken();
                    if (token.getType() == Token.TK_ENDLINE) {
                        
                    }else{throw new Exception("Exception message");}
                }else{throw new Exception("Exception message");}
            
        }else{throw new Exception("Exception message");}
    }

    public void SFR() throws Exception{
        token = scanner.nextToken();
        if (token.getType() == Token.L_BRACKET) {
            token = scanner.nextToken();
            
                token = scanner.nextToken();
                if (token.getType() == Token.R_BRACKET) {
                    token = scanner.nextToken();
                    if (token.getType() == Token.TK_ENDLINE) {
                        
                    }else{throw new Exception("Exception message");}
                }else{throw new Exception("Exception message");}
            
        }else{throw new Exception("Exception message");}
    }

    public void C() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.L_BRACKET) {
            token = scanner.nextToken();
            if(token.getType() == Token.VAR_BOOL || token.getType() == Token.TK_BOOL){
                RTB();
                if (token.getType() == Token.R_BRACKET) {
                    token = scanner.nextToken();
                    if (token.getType() == Token.L_CBRACKET) {
                        S();
                    }else{throw new Exception("Exception message");}
                }else{throw new Exception("Exception message");}
            }else{throw new Exception("Exception message");}
        }else{throw new Exception("Exception message");}
    }

    public void RTB() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.TK_COMP) {
            token = scanner.nextToken();
            if (token.getType() == Token.VAR_BOOL || token.getType() == Token.TK_BOOL) {
                
            }else{throw new Exception("Exception message");}
        }
    }

    public void RTD() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.TK_OP) {
            token = scanner.nextToken();
            if (token.getType() == Token.VAR_DOUBLE || token.getType() == Token.TK_DOUBLE) {
                
            }else{throw new Exception("Exception message");}
        }
    }

    public void RTI() throws Exception {
        token = scanner.nextToken();
        if (token.getType() == Token.TK_OP) {
            token = scanner.nextToken();
            if (token.getType() == Token.VAR_INT || token.getType() == Token.TK_INT) {
                
            }else{throw new Exception("Exception message");}
        }
    }

}
