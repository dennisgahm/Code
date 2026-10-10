import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChristianRun extends JPanel implements ActionListener, KeyListener {
    int px=3, py=11, dx=0, dy=0, ticks=0, length=5, score=0, state=0;
    int[] sx=new int[660], sy=new int[660];
    Timer timer;
    public ChristianRun() {
        setPreferredSize(new Dimension(900,780));
        setBackground(new Color(18,25,36));
        setFocusable(true);
        addKeyListener(this);
        reset();
        timer=new Timer(100,this);
        timer.start();
    }
    public static void main(String[] args) {
        JFrame f=new JFrame("Christian's Run - Escape to Church");
        ChristianRun game=new ChristianRun();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(game); f.pack(); f.setLocationRelativeTo(null); f.setResizable(false);
        f.setVisible(true); game.requestFocusInWindow();
    }
    void reset() {
        px=3; py=11; dx=0; dy=0; ticks=0; length=5; score=0; state=0;
        for(int i=0;i<660;i++){ sx[i]=21; sy[i]=11; }
        repaint();
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        int k=e.getKeyCode();
        if(k==KeyEvent.VK_R){ reset(); return; }
        if(state!=0) return;
        if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_A){dx=-1;dy=0;}
        if(k==KeyEvent.VK_RIGHT || k==KeyEvent.VK_D){dx=1;dy=0;}
        if(k==KeyEvent.VK_UP || k==KeyEvent.VK_W){dx=0;dy=-1;}
        if(k==KeyEvent.VK_DOWN || k==KeyEvent.VK_S){dx=0;dy=1;}
        if(k==KeyEvent.VK_SPACE){dx=0;dy=0;}
        if(k==KeyEvent.VK_E && px>=26 && py<=4){state=2;}
        repaint();
    }
    boolean hit() {
        for(int i=0;i<length;i++) if(px==sx[i] && py==sy[i]) return true;
        return false;
    }
    public void actionPerformed(ActionEvent e) {
        if(state!=0) return;
        ticks++;
        px=Math.max(0,Math.min(29,px+dx)); py=Math.max(0,Math.min(21,py+dy));
        if(hit()){state=1; repaint(); return;}
        if(ticks%14==0 && length<650) length++;
        if(ticks%2==0){
            for(int i=length-1;i>0;i--){sx[i]=sx[i-1];sy[i]=sy[i-1];}
            if(Math.abs(px-sx[0])>=Math.abs(py-sy[0])) {
                if(px>sx[0])sx[0]++; else if(px<sx[0])sx[0]--;
                else if(py>sy[0])sy[0]++; else if(py<sy[0])sy[0]--;
            } else {
                if(py>sy[0])sy[0]++; else if(py<sy[0])sy[0]--;
            }
        }
        score+=length;
        if(hit()) state=1;
        repaint();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("SansSerif",Font.BOLD,25));
        g.setColor(new Color(242,210,112));
        g.drawString("CHRISTIAN'S RUN",22,34);
        g.setFont(new Font("SansSerif",Font.PLAIN,16));
        g.setColor(Color.WHITE);
        g.drawString("Score: "+score+"    Snake length: "+length+"    Time: "+(ticks/10)+"s",22,61);
        g.drawString("Arrows / WASD: run   |   Space: stop   |   E: enter near church   |   R: restart",22,86);
        g.setColor(new Color(25,39,49)); g.fillRect(0,100,900,660);
        g.setColor(new Color(35,51,61));
        for(int i=0;i<=30;i++) g.drawLine(i*30,100,i*30,760);
        for(int i=0;i<=22;i++) g.drawLine(0,100+i*30,900,100+i*30);
        g.setColor(new Color(70,95,73)); g.fillRect(780,100,120,150);
        g.setColor(new Color(233,220,185)); g.fillRect(812,146,76,90);
        g.setColor(new Color(156,91,80));
        g.fillPolygon(new int[]{802,850,898},new int[]{148,110,148},3);
        g.setColor(new Color(110,71,42)); g.fillRect(841,202,20,34);
        g.setColor(Color.WHITE); g.fillRect(847,112,5,25);g.fillRect(839,119,21,5);
        g.setFont(new Font("SansSerif",Font.BOLD,13));g.drawString("CHURCH",822,177);
        g.setColor(new Color(119,209,88));
        for(int i=length-1;i>=0;i--)g.fillOval(sx[i]*30+3,sy[i]*30+103,24,24);
        g.setColor(new Color(229,80,71));g.fillOval(sx[0]*30+4,sy[0]*30+104,22,22);
        g.setColor(Color.WHITE);g.fillOval(sx[0]*30+7,sy[0]*30+109,5,5);g.fillOval(sx[0]*30+18,sy[0]*30+109,5,5);
        g.setColor(new Color(92,169,245));g.fillRect(px*30+10,py*30+112,10,10);
        g.setColor(new Color(250,209,170));g.fillOval(px*30+9,py*30+102,12,12);
        g.setColor(Color.WHITE);g.drawLine(px*30+10,py*30+121,px*30+7,py*30+128);g.drawLine(px*30+19,py*30+121,px*30+22,py*30+128);
        g.drawLine(px*30+10,py*30+115,px*30+5,py*30+120);g.drawLine(px*30+20,py*30+115,px*30+25,py*30+120);
        g.setFont(new Font("SansSerif",Font.BOLD,11));g.drawString("Christian",Math.min(838,px*30),py*30+100);
        if(state!=0){
            g.setColor(new Color(12,18,28));g.fillRoundRect(180,320,540,170,24,24);
            g.setFont(new Font("SansSerif",Font.BOLD,28));g.setColor(Color.WHITE);
            if(state==2)g.drawString("SAFE INSIDE THE CHURCH!",230,365);
            else g.drawString("THE SNAKE CAUGHT CHRISTIAN",202,365);
            g.setFont(new Font("SansSerif",Font.PLAIN,20));
            g.drawString("Final score: "+score+"   |   Snake length: "+length,250,405);
            g.drawString("Press R to try again",348,452);
        }
    }
}
