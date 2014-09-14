package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.F;
import models.EContinente;
import models.InscricaoAberta;
import models.InscricaoLimitada;
import models.Usuario;
import models.Viagem;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;


public class GeradordeExemplos {
	
	private static GenericDAO dao = new GenericDAOImpl(); 
	private final static String DATA = Calendar.getInstance().getTime().toString();
	

	private static Usuario usuario1;
	private static Usuario usuario2;
	private static Usuario usuario3;
	private static Usuario usuario4;
	private static Usuario usuario5;
	private static Usuario usuario6;
	private static Usuario usuario7;
	private static Usuario usuario8;
	private static Usuario usuario9;
	private static Usuario usuario10;
	private static Usuario usuario11;
	private static Usuario usuario12;
	private static Usuario usuario13;
	private static Usuario usuario14;
	private static Usuario usuario15;
	private static Usuario usuario16;
	private static Usuario usuario17;
	private static Usuario usuario18;
	private static Usuario usuario19;
	private static Usuario usuario20;
	private static Usuario usuario21;
	private static Usuario usuario22;
	private static Usuario usuario23;
	private static Usuario usuario24;
	private static Usuario usuario25;
	private static Usuario usuario26;
	private static Usuario usuario27;
	private static Usuario usuario28;
	private static Usuario usuario29;
	private static Usuario usuario30;
	private static Usuario usuario31;
	private static Usuario usuario32;
	private static Usuario usuario33;
	private static Usuario usuario34;
	private static Usuario usuario35;
	private static Usuario usuario36;
	private static Usuario usuario37;
	private static Usuario usuario38;
	private static Usuario usuario39;
	private static Usuario usuario40;
	private static Usuario administrador;

	

	private static Viagem viagem1;
	private static Viagem viagem2;
	private static Viagem viagem3;
	private static Viagem viagem4;
	private static Viagem viagem5;
	private static Viagem viagem6;
	private static Viagem viagem7;
	private static Viagem viagem8;
	private static Viagem viagem9;
	private static Viagem viagem10;
	private static Viagem viagem11;
	private static Viagem viagem12;
	private static Viagem viagem13;
	private static Viagem viagem14;
	private static Viagem viagem15;
	private static Viagem viagem16;
	private static Viagem viagem17;
	private static Viagem viagem18;
	private static Viagem viagem19;
	private static Viagem viagem20;
	private static Viagem viagem21;
	private static Viagem viagem22;
	private static Viagem viagem23;
	private static Viagem viagem24;
	private static Viagem viagem25;
	private static Viagem viagem26;
	private static Viagem viagem27;
	private static Viagem viagem28;
	private static Viagem viagem29;
	private static Viagem viagem30;
	

	
	
	@Transactional
	public static void gera() {
		JPA.withTransaction(new F.Callback0() {
			public void invoke() throws Throwable {
					usuario1 = new Usuario("jose", "jose@jose", "password");
					usuario2 = new Usuario("maria", "maria@maria", "password");
					usuario3 = new Usuario("joao", "joao@joao", "password");
					usuario4 = new Usuario("lucas", "lucas@lucas", "password");
					usuario5 = new Usuario("pedro", "pedro@pedro", "password");
					usuario6 = new Usuario("carlos", "carlos@carlos", "password");
					usuario7 = new Usuario("nazareno", "nazareno@nazareno", "password");
					usuario8 = new Usuario("arthur", "arthur@arthur", "password");
					usuario9 = new Usuario("iago", "iago@iago", "password");
					usuario10 = new Usuario("marcos", "marcos@marcos", "password");
					usuario11 = new Usuario("jessica", "jessica", "password");
					usuario12 = new Usuario("bruno", "bruno@bruno", "password");
					usuario13 = new Usuario("victor", "victor@victor", "password");
					usuario14 = new Usuario("igor", "igor@igor", "password");
					usuario15 = new Usuario("nara", "nara@nara", "password");
					
					usuario16 = new Usuario("josy", "josy@josy", "password");
					usuario17 = new Usuario("mari", "mari@mari", "password");
					usuario18 = new Usuario("joaop", "joaop@joaop", "password");
					usuario19 = new Usuario("luan", "luan@luan", "password");
					usuario20 = new Usuario("peter", "peter@peter", "password");
					usuario21 = new Usuario("carlosE", "carlose@carlose", "password");
					usuario22 = new Usuario("naza", "naza@naza", "password");
					usuario23 = new Usuario("artur", "artur@artur", "password");
					usuario24 = new Usuario("yasmin", "yasmin@yasmin", "password");
					usuario25 = new Usuario("mario", "mario@mario", "password");
					usuario26 = new Usuario("jessye", "jessye@jessye", "password");
					usuario27 = new Usuario("breno", "breno@breno", "password");
					usuario28 = new Usuario("vitor", "vitor@vitor", "password");
					usuario29 = new Usuario("gina", "gina@gina", "password");
					usuario30 = new Usuario("nayara", "nayara@nayara", "password");	
					usuario31 = new Usuario("john", "john@john", "password");
					usuario32 = new Usuario("marconi", "marconi@marconi", "password");
					usuario33 = new Usuario("joaoneto", "joaoneto@joaoneto", "password");
					usuario34 = new Usuario("luciano", "luciano@luciano", "password");
					usuario35 = new Usuario("tamires", "tamires@tamires", "password");
					usuario36 = new Usuario("caio", "caio@caio", "password");
					usuario37 = new Usuario("neno", "neno@neno", "password");
					usuario38 = new Usuario("ana", "ana@ana", "password");
					usuario39 = new Usuario("ivo", "ivo@ivo", "password");
					usuario40 = new Usuario("bruna", "bruna@bruna", "password");

					
					List<Usuario> inscritosviagem1 = new ArrayList<Usuario>();
					inscritosviagem1.add(usuario1);
					inscritosviagem1.add(usuario2);
					inscritosviagem1.add(usuario3);
					inscritosviagem1.add(usuario4);
					inscritosviagem1.add(usuario5);
					inscritosviagem1.add(usuario6);
					inscritosviagem1.add(usuario7);
					inscritosviagem1.add(usuario8);
					inscritosviagem1.add(usuario9);
					inscritosviagem1.add(usuario10);
					inscritosviagem1.add(usuario11);
					inscritosviagem1.add(usuario12);
					inscritosviagem1.add(usuario13);
					inscritosviagem1.add(usuario14);
					inscritosviagem1.add(usuario15);
					inscritosviagem1.add(usuario35);
					inscritosviagem1.add(usuario26);
					inscritosviagem1.add(usuario27);
					inscritosviagem1.add(usuario28);
					inscritosviagem1.add(usuario29);
					inscritosviagem1.add(usuario40);
					inscritosviagem1.add(usuario31);

					List<Usuario> inscritosviagem2 = new ArrayList<Usuario>();
					inscritosviagem2.add(usuario1);
					inscritosviagem2.add(usuario2);
					inscritosviagem2.add(usuario3);
					inscritosviagem2.add(usuario4);
					inscritosviagem2.add(usuario5);
					inscritosviagem2.add(usuario6);
					inscritosviagem2.add(usuario7);
					inscritosviagem2.add(usuario8);
					inscritosviagem2.add(usuario9);
					inscritosviagem2.add(usuario10);
					inscritosviagem2.add(usuario11);
					inscritosviagem2.add(usuario12);
					inscritosviagem2.add(usuario13);
					inscritosviagem2.add(usuario14);
					inscritosviagem2.add(usuario15);

					List<Usuario> inscritosviagem3 = new ArrayList<Usuario>();
					inscritosviagem3.add(usuario1);
					inscritosviagem3.add(usuario2);
					inscritosviagem3.add(usuario3);
					inscritosviagem3.add(usuario4);
					inscritosviagem3.add(usuario5);
					inscritosviagem3.add(usuario6);
					inscritosviagem3.add(usuario7);
					inscritosviagem3.add(usuario8);
					inscritosviagem3.add(usuario9);
					inscritosviagem3.add(usuario10);
					inscritosviagem3.add(usuario11);
					inscritosviagem3.add(usuario12);
					inscritosviagem3.add(usuario13);
					inscritosviagem3.add(usuario14);
					inscritosviagem3.add(usuario15);

					List<Usuario> inscritosviagem4 = new ArrayList<Usuario>();
					inscritosviagem4.add(usuario1);
					inscritosviagem4.add(usuario2);
					inscritosviagem4.add(usuario3);
					inscritosviagem4.add(usuario4);
					inscritosviagem4.add(usuario5);
					inscritosviagem4.add(usuario6);
					inscritosviagem4.add(usuario7);
					inscritosviagem4.add(usuario8);
					inscritosviagem4.add(usuario9);
					inscritosviagem4.add(usuario10);

					List<Usuario> inscritosviagem5 = new ArrayList<Usuario>();
					inscritosviagem5.add(usuario1);
					inscritosviagem5.add(usuario2);
					inscritosviagem5.add(usuario3);
					inscritosviagem5.add(usuario4);
					inscritosviagem5.add(usuario5);
					inscritosviagem5.add(usuario6);
					inscritosviagem5.add(usuario7);
					inscritosviagem5.add(usuario8);
					inscritosviagem5.add(usuario9);
					inscritosviagem5.add(usuario10);

					List<Usuario> inscritosviagem6 = new ArrayList<Usuario>();
					inscritosviagem6.add(usuario1);
					inscritosviagem6.add(usuario2);
					inscritosviagem6.add(usuario3);
					inscritosviagem6.add(usuario4);
					inscritosviagem6.add(usuario5);
					inscritosviagem6.add(usuario6);
					inscritosviagem6.add(usuario7);
					inscritosviagem6.add(usuario8);
					inscritosviagem6.add(usuario9);
					inscritosviagem6.add(usuario10);

					List<Usuario> inscritosviagem7 = new ArrayList<Usuario>();
					inscritosviagem7.add(usuario1);
					inscritosviagem7.add(usuario2);
					inscritosviagem7.add(usuario3);
					inscritosviagem7.add(usuario4);
					inscritosviagem7.add(usuario5);

					

					administrador = new Usuario("Jose", "jose@gmail.com", "password");

					
					viagem1 = new Viagem(EContinente.EUROPA, "França",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem1.setInscricaoStrategy(new InscricaoAberta());
					viagem2 = new Viagem(EContinente.EUROPA, "Espanha",DATA, DATA, "Plano X", inscritosviagem2, usuario39);
					viagem2.setInscricaoStrategy(new InscricaoAberta());
					viagem3 = new Viagem(EContinente.EUROPA, "Italia",DATA, DATA, "Plano X", inscritosviagem3, usuario39);
					viagem3.setInscricaoStrategy(new InscricaoLimitada());
					viagem3.setSenha("senha");
					viagem4 = new Viagem(EContinente.AMERICA_LATINA, "Brasil",DATA, DATA, "Plano X", inscritosviagem4, usuario39);
					viagem4.setInscricaoStrategy(new InscricaoLimitada());
					viagem4.setSenha("senha");
					viagem5 = new Viagem(EContinente.AMERICA_LATINA, "Chile",DATA, DATA, "Plano X", inscritosviagem5, usuario39);
					viagem5.setInscricaoStrategy(new InscricaoAberta());
					viagem6 = new Viagem(EContinente.AMERICA_LATINA, "Argentina",DATA, DATA, "Plano X", inscritosviagem6, usuario39);
					viagem6.setInscricaoStrategy(new InscricaoAberta());
					viagem7 = new Viagem(EContinente.AMERICA_LATINA, "Paraguai",DATA, DATA, "Plano X", inscritosviagem7, usuario39);
					viagem7.setInscricaoStrategy(new InscricaoAberta());
					viagem8 = new Viagem(EContinente.AMERICA_DO_NORTE, "Estados Unidos",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem8.setInscricaoStrategy(new InscricaoAberta());
					viagem9 = new Viagem(EContinente.AMERICA_DO_NORTE, "Canadá",DATA, DATA, "Plano X", inscritosviagem2, usuario39);
					viagem9.setInscricaoStrategy(new InscricaoAberta());
					viagem10 = new Viagem(EContinente.AMERICA_DO_NORTE, "Canadá",DATA, DATA, "Plano X", inscritosviagem3, usuario39);
					viagem10.setInscricaoStrategy(new InscricaoAberta());
					viagem11 = new Viagem(EContinente.AMERICA_LATINA, "Bolivia",DATA, DATA, "Plano X", inscritosviagem5, usuario39);
					viagem11.setInscricaoStrategy(new InscricaoAberta());
					viagem12 = new Viagem(EContinente.AMERICA_LATINA, "Uruguai",DATA, DATA, "Plano X", inscritosviagem6, usuario39);
					viagem12.setInscricaoStrategy(new InscricaoAberta());
					viagem13 = new Viagem(EContinente.AMERICA_LATINA, "Chile",DATA, DATA, "Plano X", inscritosviagem4, usuario39);
					viagem13.setInscricaoStrategy(new InscricaoAberta());
					viagem14 = new Viagem(EContinente.AFRICA, "Angola",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem14.setInscricaoStrategy(new InscricaoAberta());
					viagem15 = new Viagem(EContinente.AFRICA, "Nigeria",DATA, DATA, "Plano X", inscritosviagem4, usuario39);
					viagem15.setInscricaoStrategy(new InscricaoAberta());
					viagem16 = new Viagem(EContinente.AFRICA, "Marrocos",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem16.setInscricaoStrategy(new InscricaoAberta());
					viagem17 = new Viagem(EContinente.AFRICA, "Moçambique",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem17.setInscricaoStrategy(new InscricaoAberta());
					viagem18 = new Viagem(EContinente.AFRICA, "Congo",DATA, DATA, "Plano X", inscritosviagem3, usuario39);
					viagem18.setInscricaoStrategy(new InscricaoAberta());
					viagem19 = new Viagem(EContinente.OCEANIA, "Australia",DATA, DATA, "Plano X", inscritosviagem2, usuario39);
					viagem19.setInscricaoStrategy(new InscricaoAberta());
					viagem20 = new Viagem(EContinente.OCEANIA, "Nova Zelandia",DATA, DATA, "Plano X", inscritosviagem7, usuario39);
					viagem20.setInscricaoStrategy(new InscricaoAberta());
					viagem21 = new Viagem(EContinente.ASIA, "China",DATA, DATA, "Plano X", inscritosviagem5, usuario39);
					viagem21.setInscricaoStrategy(new InscricaoAberta());
					viagem21 = new Viagem(EContinente.ASIA, "Iraque",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem21.setInscricaoStrategy(new InscricaoAberta());
					viagem22 = new Viagem(EContinente.ASIA, "Malasia",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem22.setInscricaoStrategy(new InscricaoAberta());
					viagem23 = new Viagem(EContinente.ASIA, "Tailandia",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem23.setInscricaoStrategy(new InscricaoAberta());
					viagem24 = new Viagem(EContinente.ASIA, "Afeganistão",DATA, DATA, "Plano X", inscritosviagem2, usuario39);
					viagem24.setInscricaoStrategy(new InscricaoAberta());
					viagem25 = new Viagem(EContinente.ASIA, "Paquistão",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem25.setInscricaoStrategy(new InscricaoAberta());
					viagem26 = new Viagem(EContinente.ASIA, "Israel",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem26.setInscricaoStrategy(new InscricaoAberta());
					viagem27 = new Viagem(EContinente.EUROPA, "Grecia",DATA, DATA, "Plano X", inscritosviagem1, usuario39);
					viagem27.setInscricaoStrategy(new InscricaoAberta());
					viagem28 = new Viagem(EContinente.EUROPA, "Alemanha",DATA, DATA, "Plano X", inscritosviagem5, usuario39);
					viagem28.setInscricaoStrategy(new InscricaoAberta());
					viagem29 = new Viagem(EContinente.EUROPA, "Malta",DATA, DATA, "Plano X", inscritosviagem4, usuario39);
					viagem29.setInscricaoStrategy(new InscricaoAberta());
					viagem30 = new Viagem(EContinente.EUROPA, "Irlanda",DATA, DATA, "Plano X", inscritosviagem5, usuario39);
					viagem30.setInscricaoStrategy(new InscricaoAberta());
					
					
					
					
					
					

				

					getDao().persist(administrador);
					getDao().persist(usuario1);
					getDao().persist(usuario2);
					getDao().persist(usuario3);
					getDao().persist(usuario4);
					getDao().persist(usuario5);
					getDao().persist(usuario6);
					getDao().persist(usuario7);
					getDao().persist(usuario8);
					getDao().persist(usuario9);
					getDao().persist(usuario10);
					getDao().persist(usuario11);
					getDao().persist(usuario12);
					getDao().persist(usuario13);
					getDao().persist(usuario14);
					getDao().persist(usuario15);
					getDao().persist(usuario26);
					getDao().persist(usuario27);
					getDao().persist(usuario28);
					getDao().persist(usuario29);
					getDao().persist(usuario31);
					getDao().persist(usuario35);
					getDao().persist(usuario40);
										

					getDao().persist(viagem1);
					getDao().persist(viagem2);
					getDao().persist(viagem3);
					getDao().persist(viagem4);
					getDao().persist(viagem5);
					getDao().persist(viagem6);
					getDao().persist(viagem7);
					getDao().persist(viagem8);
					getDao().persist(viagem9);
					getDao().persist(viagem10);
					getDao().persist(viagem11);
					getDao().persist(viagem12);
					getDao().persist(viagem13);
					getDao().persist(viagem14);
					getDao().persist(viagem15);
					getDao().persist(viagem16);
					getDao().persist(viagem17);
					getDao().persist(viagem18);
					getDao().persist(viagem19);
					getDao().persist(viagem20);
					getDao().persist(viagem21);
					getDao().persist(viagem22);
					getDao().persist(viagem23);
					getDao().persist(viagem24);
					getDao().persist(viagem25);
					getDao().persist(viagem26);
					getDao().persist(viagem27);
					getDao().persist(viagem28);
					getDao().persist(viagem29);
					getDao().persist(viagem30);
					
					getDao().flush();
				}
		});
	}
	
	private static GenericDAO getDao() {
		return dao;
	}
	
}
