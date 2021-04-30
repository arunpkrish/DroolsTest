package com.arun.demo.config;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolConfig {

	private KieServices kieServices = KieServices.Factory.get();

	private KieFileSystem getKieFileSystem() throws IOException {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource("rule.xls"));
		return kieFileSystem;

	}

	@Bean
	public KieContainer getKieContainer() throws IOException {
		System.out.println("Container created...");
		getKieRepository();
		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
		return kContainer;

	}

	private void getKieRepository() {
		final KieRepository kieRepository = kieServices.getRepository();
		kieRepository.addKieModule(new KieModule() {
			public ReleaseId getReleaseId() {
				return kieRepository.getDefaultReleaseId();
			}
		});
	}

	@Bean
	public KieSession getKieSession() throws IOException {
		System.out.println("session created...");
		return getKieContainer().newKieSession();

	}

//	private KieServices kieServices = KieServices.Factory.get();
//
//	private void getKieRepository() {
//		final KieRepository kieRepository = kieServices.getRepository();
//		kieRepository.addKieModule(new KieModule() {
//			public ReleaseId getReleaseId() {
//				return kieRepository.getDefaultReleaseId();
//			}
//		});
//	}	
//	
//	private KieFileSystem getKieFileSystem() throws IOException {
//		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//		kieFileSystem.write(ResourceFactory.newClassPathResource("rule.xls"));
//		return kieFileSystem;
//	}
//	
//	@Bean
//	public KieContainer getKieContainer() throws IOException {
//		
//		getKieRepository();
//		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
//		kb.buildAll();
//
//		KieModule kieModule = kb.getKieModule();
//		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//
//		return kContainer;
//	}
//
//
//	@Bean
//	public KieSession getKieSession() throws IOException {
//
//		return getKieContainer().newKieSession();
//
//	}

//	public KieSession getKieSession(Resource dt) {
//		KieFileSystem kieFileSystem = kieServices.newKieFileSystem().write(dt);
//
//		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
//
//		KieRepository kieRepository = kieServices.getRepository();
//
//		ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
//
//		KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
//
//		KieSession ksession = kieContainer.newKieSession();
//
//		return ksession;
//	}

//	public KieSession getKieSession() {
//		getKieRepository();
//		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//
//		kieFileSystem.write(ResourceFactory.newClassPathResource("rule.xls"));
//
//		KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
//		kb.buildAll();
//		KieModule kieModule = kb.getKieModule();
//
//		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//
//		return kContainer.newKieSession();
//
//	}

//	/*
//	 * Can be used for debugging Input excelFile example:
//	 * com/baeldung/drools/rules/Discount.xls
//	 */
//	public String getDrlFromExcel(String excelFile) {
//		DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
//		configuration.setInputType(DecisionTableInputType.XLS);
//
//		Resource dt = ResourceFactory.newClassPathResource(excelFile, getClass());
//
//		DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();
//
//		String drl = decisionTableProvider.loadFromResource(dt, null);
//
//		return drl;
//	}
}
