import java.io.*;

/**
 * Delete "-" and Change "," to "." in all files in a folder
 *
 * Created by YDeathYLORD on 20.04.2015.
 */
public class main {

	public static void dostuff(String dirName){

		//Choosefile choose = new Choosefile();
		//choose.run();


		//String dirName = "D:\\work\\05\\Gleeble 2015.03.05 al-ni\\06 two material\\6 Ni 0.1\\test";
		//String dirName = choose.getDirPath();
		File dir = new File(dirName);

		if(dir.isDirectory()){//если папка, то
			String s[] = dir.list();//получаем список файлов

			for(int i=0; i<s.length; i++){//цикл по файлам в папке
				File f=new File(dirName + "\\" + s[i]);//полный путь к файлу

				if(f.isDirectory())
					System.out.println(s[i]+ " не является файлом!");
				else
				{//здесь обрабатываем каждый файл
					try {
						FileReader fr = new FileReader(f.getPath());
						BufferedReader br = new BufferedReader(fr);

						// Параметр append указывает, должны ли данные дозаписываться в конец файла (если параметр равен true)
						FileWriter writer = new FileWriter(f.getPath()+"YD", true);
						BufferedWriter bw = new BufferedWriter(writer);

						String str;
						while ((str=br.readLine())!=null){
							str = str.replace(",", ".");
							str = str.replace("-", "");
							bw.write(str + "\r\n");
						}

						br.close();
						bw.close();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else
			System.out.println(dirName+ " не является каталогом");
	}

}
