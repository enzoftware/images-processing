import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.*;

public class CannyFilter {

    public static void main(String[] args) throws Throwable {
        BufferedImage image = ImageIO.read(new File("Images/photo.jpg"));
        File output = new File("Images/output.jpg");
        int height = image.getHeight();
        int width = image.getWidth();
        int[][] Cannys = new int[][]{{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
        int fullPixelsR = 0;
        int fullPixelsG = 0;
        int fullPixelsB = 0;
        Color color;
        int i , j;
        int aux , aux2;

        for(i = 0 ; i < height -2 ; i++){
            for ( j = 0 ; j < width -2 ; j++){
                for( aux = 0 ; aux < 3 ; aux++){
                    for (aux2 = 0 ; aux2 < 3 ; aux2++){
                        color = new Color(image.getRGB(j+aux,i+aux2));
                        fullPixelsR += color.getRed() * Cannys[aux2][aux];
                        fullPixelsG += color.getGreen() * Cannys[aux2][aux];
                        fullPixelsB += color.getBlue() * Cannys[aux2][aux];
                    }
                }
                if (fullPixelsR < 0)
                {
                    fullPixelsR = 0;
                }
                else
                {
                    if (fullPixelsR > 255)
                    {
                        fullPixelsR = 255;
                    }
                }
                if (fullPixelsG < 0)
                {
                    fullPixelsG = 0;
                }
                else
                {
                    if (fullPixelsG > 255)
                    {
                        fullPixelsG = 255;
                    }
                }
                if (fullPixelsB < 0)
                {
                    fullPixelsB = 0;
                }
                else
                {
                    if (fullPixelsB > 255)
                    {
                        fullPixelsB = 255;
                    }

                }
                int cinza = (fullPixelsR + fullPixelsG + fullPixelsB) / 3;
                image.setRGB(j+1,i+1,new Color(cinza,cinza,cinza).getRGB());
                fullPixelsR = 0;
                fullPixelsG = 0;
                fullPixelsB = 0;
                cinza = 0;
            }
        }
        ImageIO.write(image,"jpg",output);
    }

}



/*
private void btnBorde_Click(object sender, EventArgs e)
         {
             Bitmap Imagem = new Bitmap(pbImagen.Image);
             int Largura = Imagem.Width;
             int Altura = Imagem.Height;
             int[,] Cannys = new int[,] { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };
             int todosPixelsR = 0;
             int todosPixelsG = 0;
             int todosPixelsB = 0;
             int i;
             int j;
             int aux, aux2;
             Bitmap NovaImagem = new Bitmap(Largura, Altura); //para trabalhar com a imagem, novo bmp com o mesmo tamanho da img original
             for (i = 0; i < Altura - 2; i++)
             {
                 for (j = 0; j < Largura - 2; j++)
                 {
                     for (aux = 0; aux < 3; aux++)
                     {
                         for (aux2 = 0; aux2 < 3; aux2++)
                         {
                             todosPixelsR += Imagem.GetPixel(j + aux, i + aux2).R * Cannys[aux2, aux];
                             todosPixelsG += Imagem.GetPixel(j + aux, i + aux2).G * Cannys[aux2, aux];
                             todosPixelsB += Imagem.GetPixel(j + aux, i + aux2).B * Cannys[aux2, aux];
                         }

                     }


                     if (todosPixelsR < 0)
                     {
                         todosPixelsR = 0;
                     }
                     else
                     {
                         if (todosPixelsR > 255)
                         {
                             todosPixelsR = 255;
                         }
                     }
                     if (todosPixelsG < 0)
                     {
                         todosPixelsG = 0;
                     }
                     else
                     {
                         if (todosPixelsG > 255)
                         {
                             todosPixelsG = 255;
                         }
                     }
                     if (todosPixelsB < 0)
                     {
                         todosPixelsB = 0;
                     }
                     else
                     {
                         if (todosPixelsB > 255)
                         {
                             todosPixelsB = 255;
                         }

                     }
                     int cinza = (todosPixelsR + todosPixelsG + todosPixelsB) / 3;


                     NovaImagem.SetPixel(j + 1, i + 1, Color.FromArgb(255, cinza, cinza, cinza));
                     todosPixelsR = 0;
                     todosPixelsG = 0;
                     todosPixelsB = 0;
                     cinza = 0;

                 }
             }
             pbImagenResultado.Image = NovaImagem;
        }
*/