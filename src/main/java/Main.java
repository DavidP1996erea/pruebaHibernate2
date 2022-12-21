import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Scanner;

public class Main {
    private  static SessionFactory sessionFactory;
    public static void main(String[] args) {

        try {
            setUp();
            menu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    private static void menu(){

        Scanner sc = new Scanner(System.in);
        int opcion;

        opciones();
        opcion=sc.nextInt();

        while (opcion!=4){

            switch (opcion){
                case 1:

                    guardar();
                    break;

                case 2:
                    System.out.println("Introduzca el id de la persona");
                    int idEliminar = sc.nextInt();
                    eliminar(idEliminar);
                    break;

                case 3:
                    System.out.println("Introduzca el id de la persona");
                    int idActualizar = sc.nextInt();
                    actualizar(idActualizar);
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

            opciones();
            opcion=sc.nextInt();

        }

    }

    /**
     *
     */
    private static void opciones(){
        System.out.println("1. Insertar");
        System.out.println("2. Eliminar");
        System.out.println("3. Actualizar");
        System.out.println("4. Salir");
    }
    protected static void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }


    private static void guardar() {
        PlayerEntidad persona = new PlayerEntidad("Pocholo", "12314", "david@ejej.com");
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(persona);
        transaction.commit();
        System.out.println(id);
        sessionFactory.close();
    }

    private static void eliminar(int id){
        // Se abre la sesión
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        PlayerEntidad persona = session.get(PlayerEntidad.class, id);
        // Se borra esa persona
        session.delete(persona);
        transaction.commit();
        sessionFactory.close();
    }


    private static void actualizar(int id){
        // Se abre la sesión
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // Recoge una persona con el id
        PlayerEntidad persona = session.get(PlayerEntidad.class, id);
        // Se cambia el parámetro que se quiera seteando su atributo
        persona.setNick("Ruben");
        // Se actualiza esa persona
        session.saveOrUpdate(persona);
        transaction.commit();
        sessionFactory.close();
    }


}
