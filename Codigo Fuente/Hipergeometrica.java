import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.Image;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
public class Hipergeometrica extends JFrame{
	private JCheckBox Puntual,Generadora,Esperanza,Varianza,Acumulada;
	private JLabel etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5;
	private JButton calcular,regresar;
	private JTextField caja1,caja2,caja3,caja4;
	private JComboBox<String> opAcumulada;
	public Hipergeometrica(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setTitle("Hipergeometrica");
		setResizable(false);
		iniciaComponentes();
	}
	public void iniciaComponentes(){
		int c=191;
		etiqueta1 = new JLabel((char)c+"Que deseas saber (ingresa parametros)?");
		etiqueta1.setBounds(10,10,300,30);
		add(etiqueta1);
		etiqueta2 = new JLabel("n:");
		etiqueta2.setBounds(10,40,30,30);
		add(etiqueta2);
		etiqueta3 = new JLabel("r1:");
		etiqueta3.setBounds(100,40,30,30);
		add(etiqueta3);
		etiqueta4 = new JLabel("r2:");
		etiqueta4.setBounds(190,40,30,30);
		add(etiqueta4);
		etiqueta5 = new JLabel("x:");
		etiqueta5.setBounds(280,40,30,30);
		add(etiqueta5);
		caja1 = new JTextField();
		caja1.setBounds(25,40,75,30);
		add(caja1);
		caja1.setForeground(new Color(0,0,255));
		caja2 = new JTextField();
		caja2.setBounds(115,40,75,30);
		add(caja2);
		caja2.setForeground(new Color(0,0,255));
		caja3 = new JTextField();
		caja3.setBounds(205,40,75,30);
		add(caja3);
		caja3.setForeground(new Color(0,0,255));
		caja4 = new JTextField();
		caja4.setBounds(295,40,75,30);
		add(caja4);
		caja4.setForeground(new Color(0,0,255));
		Puntual = new JCheckBox("Probabilidad Puntual");
		Puntual.setBounds(10,70,150,30);
		add(Puntual);
		Generadora = new JCheckBox("Funcion Generadora de Momentos");
		Generadora.setBounds(10,100,250,30);
		add(Generadora);
		Esperanza = new JCheckBox("Esperanza");
		Esperanza.setBounds(10,130,100,30);
		add(Esperanza);
		Varianza = new JCheckBox("Varianza");
		Varianza.setBounds(10,160,100,30);
		add(Varianza);
		Acumulada = new JCheckBox("Probabilidad Acumulada");
		Acumulada.setBounds(10,190,200,30);
		add(Acumulada);
		eventoAcumulada();
		opAcumulada = new JComboBox<>();
		opAcumulada.setBounds(210,190,100,30);
		add(opAcumulada);
		opAcumulada.addItem("X<=x");
		opAcumulada.addItem("X<x");
		opAcumulada.addItem("X>=x");
		opAcumulada.addItem("X>x");
		opAcumulada.setEnabled(false);
		calcular = new JButton("Calcular");
		calcular.setBounds(10,230,165,65);
		add(calcular);
		calcular.setHorizontalAlignment(SwingConstants.LEFT);
		calcular.setBackground(new Color(123,145,167));
		eventoCalcula();
		iconosC();
		regresar = new JButton("Regresar");
		regresar.setBounds(195,230,165,65);
		add(regresar);
		regresar.setHorizontalAlignment(SwingConstants.LEFT);
		regresar.setBackground(new Color(123,145,167));
		eventoRegresa();
		iconosR();
	}
	public void eventoAcumulada(){
		ChangeListener listener = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent ev){
				if(Acumulada.isSelected()==true){
					opAcumulada.setEnabled(true);
				}else{
					opAcumulada.setEnabled(false);
				}
			}
		};
		Acumulada.addChangeListener(listener);
	}
	public BigDecimal calculaPuntual(int n,int r1,int r2,int x){
		BigInteger c1,c2,c3;
		BigDecimal resultado;
		c1=Calculos.Combinacion(r1,x);
		c2=Calculos.Combinacion(r2,n-x);
		c3=Calculos.Combinacion(r1+r2,n);
		resultado = new BigDecimal((c1.multiply(c2)),MathContext.DECIMAL64);
		return resultado.divide(new BigDecimal(c3,MathContext.DECIMAL64),MathContext.DECIMAL64);
	}
	public String calculaGeneradora(){
		return "No existe";
	}
	public double calculaEsperanza(int n,int r1,int r2){
		int r=r1+r2;
		double y=(int)n,i;
		i=((double)r1)/((double)r);
		return y*i;
	}
	public double calculaVarianza(int n,int r1,int r2){
		double i1,i2,y;
		i1=((double)r1)/((double)(r1+r2));
		i2=((double)(n-1))/((double)(r1+r2-1));
		y=(double)n;
		return y*i1*(1-i1)*(1-i2);
	}
	public BigDecimal acumuladaMenorI(int n,int r1,int r2,int x){
		BigDecimal resultado = BigDecimal.ZERO,aux;
		int i;
		for(i=Calculos.max(0,n-r2);i<=x;i++){
			aux=calculaPuntual(n,r1,r2,i);
			resultado=resultado.add(aux);
		}
		return resultado;
	}
	public BigDecimal calculaAcumulada(int n,int r1,int r2,int x){
		BigDecimal resultado=BigDecimal.ZERO;
		BigDecimal aux=BigDecimal.ONE;
		if(opAcumulada.getSelectedItem().equals("X>=x")){
			return aux.subtract(acumuladaMenorI(n,r1,r2,x-1));
		}else if(opAcumulada.getSelectedItem().equals("X>x")){
			return aux.subtract(acumuladaMenorI(n,r1,r2,x));
		}else if(opAcumulada.getSelectedItem().equals("X<=x")){
				return acumuladaMenorI(n,r1,r2,x);
		}else if(opAcumulada.getSelectedItem().equals("X<x")){
				return acumuladaMenorI(n,r1,r2,x).subtract(calculaPuntual(x,r1,r2,n));
		}else return resultado;
	}
	public void eventoCalcula(){
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev){
				double esperanza,varianza;
				BigDecimal puntual,acumulada;
				String generadora,resultados="";
				if(Puntual.isSelected()){
					puntual=calculaPuntual(Integer.parseInt(caja1.getText()),Integer.parseInt(caja2.getText()),Integer.parseInt(caja3.getText()),Integer.parseInt(caja4.getText()));
					resultados+="Puntual:"+puntual+"\n";
				}
				if(Generadora.isSelected()){
					generadora=calculaGeneradora();
					resultados+="Generadora:"+generadora+"\n";
				}
				if(Esperanza.isSelected()){
					int m=181;
					esperanza=calculaEsperanza(Integer.parseInt(caja1.getText()),Integer.parseInt(caja2.getText()),Integer.parseInt(caja3.getText()));
					resultados+="Esperanza "+(char)m+": "+esperanza+"\n";
				}
				if(Varianza.isSelected()){
					varianza=calculaVarianza(Integer.parseInt(caja1.getText()),Integer.parseInt(caja2.getText()),Integer.parseInt(caja3.getText()));
					resultados+="Varianza:"+varianza+"\n";
				}
				if(Acumulada.isSelected()){
					acumulada=calculaAcumulada(Integer.parseInt(caja1.getText()),Integer.parseInt(caja2.getText()),Integer.parseInt(caja3.getText()),Integer.parseInt(caja4.getText()));
					resultados+="Acumulada:"+acumulada+"\n";
				}
				JOptionPane.showMessageDialog(null,"resultados:\n"+resultados);
			}
		};
		calcular.addActionListener(listener);
	}
	public void eventoRegresa(){
		ActionListener listener = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ev){
				setVisible(false);
				Menu sa = new Menu();
				sa.setBounds(0,0,365,520);
				sa.setLocationRelativeTo(null);
				sa.setVisible(true);
			}
		};
		regresar.addActionListener(listener);
	}
	public void iconosC(){
		ImageIcon ic = new ImageIcon("iconos/calcular.png");
		ImageIcon ipc = new ImageIcon("iconos/presionado.png");
		ImageIcon irc = new ImageIcon("iconos/adelante.png");
		calcular.setIcon(new ImageIcon(ic.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		calcular.setPressedIcon(new ImageIcon(ipc.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		calcular.setRolloverIcon(new ImageIcon(irc.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
	public void iconosR(){
		ImageIcon ib = new ImageIcon("iconos/regresar.png");
		ImageIcon ipb = new ImageIcon("iconos/presionado.png");
		ImageIcon irb = new ImageIcon("iconos/atras.png");
		regresar.setIcon(new ImageIcon(ib.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		regresar.setPressedIcon(new ImageIcon(ipb.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
		regresar.setRolloverIcon(new ImageIcon(irb.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)));
	}
}