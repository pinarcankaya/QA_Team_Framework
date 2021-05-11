package Utilities;


public class ReadTxt {
    private static Object Code;
    private static Object data;
//
//    public static List<Code> returnCustomer(String filePath){
//        List<Code>all = new ArrayList<>();
//        try(BufferedReader br = new BufferedReader(new FileReader(ConfigurationReader.getProperty(filePath)))) {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            while (line != null) {
//                Code customer = new Code();
//                sb.append(line);
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//                String [] each = line.split(",");
//                customer.setData(each[0]);
//                customer.setMeta(each[1]);
//
//                all.add((Pojos.Code) Code);
//            }
//            String everything = sb.toString();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return all;
//    }
//
//
//
//    public static List<data> returnAllGender(String filePath){
//        List<data>all = new ArrayList<>();
//        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//            System.out.println(line);
//            int i=0;
//            while (line != null) {
//                data =new data();
//                ((Pojos.data) data).setName(line.split(",")[0].trim());
//                ((Pojos.data) data).setId(Integer.parseInt(line.split(",")[1].trim()));
//                sb.append(System.lineSeparator());
//                line = br.readLine();
//                System.out.println(i++);
//
//                all.add((Pojos.data) data);
//            }
//            String everything = sb.toString();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return all;
//    }
}
