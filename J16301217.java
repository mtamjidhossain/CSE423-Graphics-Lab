/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package J16301217;

/**
 *
 * @author USER
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import java.util.Scanner;
import java.lang.Math;


public class J16301217  implements GLEventListener {
    
    private GLU glu;

    public void init(GLAutoDrawable gld) {

        Scanner scanner = new Scanner(System.in);
      
        scanner.close();
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        drawCircle(gl,100);
        drawCircleL(gl,100);
       
        
        
    }

    private void drawCircle(GL2 gl, int r) {
        
       //ENTER YOUR CODE HERE
       int x=0;
       int y=r;
       int d=1-r;
       draw8Way(gl,x,y);
       while(y>=x){
           if(d<0){                     //choose E
               x++;
               d=d+(2*x+3);
           }
           else{
               x++;                     //choose SE
               y--;
               d=d+(2*x-2*y+5);
           }
           draw8Way(gl,x,y);
       }      
    }
    

    private void draw8Way(GL2 gl, int x, int y) {
        int Cx=0;
        int Cy=0;
        gl.glLineWidth(1.0f);                  //line width increase
        gl.glColor3f(1f,1f,1f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-200,0);
        gl.glVertex2d(200,0);
        gl.glVertex2d(0,-200);
        gl.glVertex2d(0,200);
        gl.glVertex2d(200,-200);
        gl.glVertex2d(-200,200);
        gl.glVertex2d(-200,-200);
        gl.glVertex2d(200,200);
        gl.glEnd();
        
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x+Cx, y+Cy);
        gl.glVertex2d(y+Cx, x+Cy);

        gl.glVertex2d(-x+Cx, y+Cy);
        gl.glVertex2d(-y+Cx, x+Cy);

        gl.glVertex2d(-x+Cx, -y+Cy);
        gl.glVertex2d(-y+Cx, -x+Cy);


        gl.glVertex2d(x+Cx, -y+Cy);
        gl.glVertex2d(y+Cx, -x+Cy);
        gl.glEnd();

    }
    
    private void drawCircleL(GL2 gl, int r) {
        
       //ENTER YOUR CODE HERE
       int x=0;
       int y=r/2;
       int d=1-r;
       draw8Way(gl,x,y);
       while(y>=x){
           if(d<0){                     //choose E
               x++;
               d=d+(2*x+3);
           }
           else{
               x++;                     //choose SE
               y--;
               d=d+(2*x-2*y+5);
           }
           draw8WayL(gl,x,y,r,r/2,0);
           draw8WayL(gl,x,y,r,0,r/2);
           draw8WayL(gl,x,y,r,-r/2,0);
           draw8WayL(gl,x,y,r,0,-r/2);
           int Cx=(int) (r*0.71);                    //Polar to cartesian: x= r*Cos45, 
           int Cy=(int) (r*0.71);                    //y= r*Sin45
           draw8WayL(gl,x,y,r,Cx/2,Cy/2);
           draw8WayL(gl,x,y,r,-Cx/2,-Cy/2);
           draw8WayL(gl,x,y,r,-Cx/2,Cy/2);
           draw8WayL(gl,x,y,r,Cx/2,-Cy/2);
       }      
    }
    
    private void draw8WayL(GL2 gl, int x, int y,int r,int Cx, int Cy) {
//        int Cx=r/2;
//        int Cy=0;
        gl.glLineWidth(1.0f);                  //line width increase
        gl.glColor3f(1f,1f,1f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(-200,0);
        gl.glVertex2d(200,0);
        gl.glVertex2d(0,-200);
        gl.glVertex2d(0,200);
        gl.glVertex2d(200,-200);
        gl.glVertex2d(-200,200);
        gl.glVertex2d(-200,-200);
        gl.glVertex2d(200,200);
        gl.glEnd();
        
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x+Cx, y+Cy);
        gl.glVertex2d(y+Cx, x+Cy);

        gl.glVertex2d(-x+Cx, y+Cy);
        gl.glVertex2d(-y+Cx, x+Cy);

        gl.glVertex2d(-x+Cx, -y+Cy);
        gl.glVertex2d(-y+Cx, -x+Cy);


        gl.glVertex2d(x+Cx, -y+Cy);
        gl.glVertex2d(y+Cx, -x+Cy);
        gl.glEnd();

    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }
    
    public static void main(String[] args) {

      //getting the capabilities object of GL2 profile        
      final GLProfile profile = GLProfile.get(GLProfile.GL2);
      GLCapabilities capabilities = new GLCapabilities(profile);
   
      // The canvas
      final GLCanvas glcanvas = new GLCanvas(capabilities);
      J16301217 drawing = new J16301217();
      glcanvas.addGLEventListener(drawing);
      glcanvas.setSize(400, 400);
   
      //creating frame
      final JFrame frame = new JFrame ("Circle");
   
      //adding canvas to frame
      frame.getContentPane().add(glcanvas);                 
      frame.setSize(frame.getContentPane().getPreferredSize());
      frame.setVisible(true);
      
      
   }
}
