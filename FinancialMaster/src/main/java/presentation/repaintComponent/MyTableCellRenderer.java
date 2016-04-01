package presentation.repaintComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer{
	private boolean isHover = false;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		JLabel label = new JLabel()  
        {  
            private static final long serialVersionUID = 1L;  
  
            protected void paintComponent(Graphics g)  
             {  
                //重载jlabel的paintComponent方法，在这个jlabel里手动画线  
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(62, 56, 49, 30));
                if (isSelected) {
					g2d.fillRect(0, 0, getWidth(), getHeight());
				}
//                if (isHover) {
//                	g2d.setColor(Color.black);
//                	g2d.fillRect(0, 0, getWidth(), getHeight());
//				}
                if (row%2 ==0) {
                	g2d.setColor(new Color(62, 56, 49, 10));
                	g2d.fillRect(0, 0, getWidth(), getHeight());
				}
                //一定要记得调用父类的paintComponent方法，不然它只会划线，不会显示文字  
                super.paintComponent(g);  
             }  
         };  
         label.setText(value != null ? value.toString() : "unknown");   
         label.setFont(new Font("Arial", Font.PLAIN, 13));
         label.setHorizontalAlignment(JLabel.CENTER);
         label.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
         
         return label; 
	}
	

}
