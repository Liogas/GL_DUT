<?php
    defined('BASEPATH') OR exit('No direct script access allowed');

    class Utilisateur extends CI_Controller {

    	public function inscription(){
    		$this->load->helper('form');
    		$this->load->library('form_validation');
    		$this->load->model('Model_utilisateur');

    		$this->form_validation->set_rules('login', 'Login', 'trim|is_unique[utilisateur.login]');
    		$this->form_validation->set_rules('password', 'Mot de passe', 'required|trim');

    		$this->form_validation->set_message('is_unique', '{field} est déjà présent dans la base.');

    		if($this->form_validation->run() === FALSE){
                    $this->load->view('head');
                    $this->load->view('header');
                    $this->load->view('Accueil/accueilVisite');
                    $this->load->view('Accueil/Inscription');
                    $this->load->view('Accueil/Connexion');
                    $this->load->view('objetFormulaire/endDiv');
                    $this->load->view('objetFormulaire/endDiv');
                    $this->load->view('errors/html/error_inscription', array('username' => $this->input->post('login')));
                    $this->load->view('footer');
    		} else {
    			$login = $this->input->post('login');
    			$pwd = $this->input->post('password');
    			$data = array('login' => $login,'password' => password_hash($pwd,PASSWORD_BCRYPT));

    			if($this->Model_utilisateur->create($data)){
    				$this->load->view('head');
                    $this->load->view('header');
                    $this->load->view('Accueil/accueilVisite');
                    $this->load->view('Accueil/Inscription');
                    $this->load->view('Accueil/Connexion');
                    $this->load->view('objetFormulaire/endDiv');
                    $this->load->view('objetFormulaire/endDiv');
                    $this->load->view('success/connexion_success');
                    $this->load->view('footer');
    			}
    		}
    	}

    	public function checkPassword($pwd){
    		$this->load->model('Model_utilisateur');
    		$login = $this->input->post('login');
    		$pwdHash = $this->Model_utilisateur->getPasswordHash($login);
    		if($pwdHash === FALSE){
    			$this->form_validation->set_message('checkPassword', 'Nom d\'utilisateur et/ou mot de passe incorrect.');
    			return FALSE;
    		}
    		if(password_verify($pwd, $pwdHash)){
    			return TRUE;
    		} else {
    			$this->form_validation->set_message('checkPassword', 'Nom d\'utilisateur et/ou mot de passe incorrect.');
    			return FALSE;
    		}
    	}

    	public function connexion(){
    		if($this->session->logged){
				redirect('utilisateur/log');
    		} else {
    			$this->load->helper('form');
    			$this->load->library('form_validation');
    			$this->load->model('Model_utilisateur');

    			$this->form_validation->set_rules('password', 'Mot de passe', 'trim|callback_checkPassword');

    			if($this->form_validation->run() === FALSE){
                    $this->load->helper('form');
                    $this->load->view('head');
                    $this->load->view('header');
                    $this->load->view('Accueil/accueilVisite');
                    $this->load->view('Accueil/Inscription');
                    $this->load->view('Accueil/Connexion');
                    $this->load->view('objetFormulaire/endDiv');
                    $this->load->view('objetFormulaire/endDiv');
                    $this->load->view('errors/html/error_connexion');
                    $this->load->view('footer');
    			} else {
    				$_SESSION['login'] = $this->input->post('login');
    				$_SESSION['logged'] = TRUE;
					redirect('utilisateur/log');
    			}	
    		}	
		}
		
		public function log(){
    		if(!$this->session->logged){
				redirect('site');
    		} else {
                $this->load->view('head');
                $this->load->view('header');
                $this->load->view('Accueil/accueilLog');
                $this->load->view('footer');			
			}
		}

    	public function espaceClient(){
            if(!$this->session->logged){
                redirect('site');               
            } else {
				$this->load->model('Model_formulaire');
				$formulaire = $this->Model_formulaire->get_all_form($_SESSION['login']);
				$this->load->view('head');
				$this->load->view('header');
				$this->load->view('espaceClient/enTete');
				if(!empty($formulaire)){
    				foreach ($formulaire as $key => $form){
						$this->load->view('espaceClient/listeForm',$form);	
					}
				} else {
					$this->load->view('espaceClient/noForm');
				}
    			$this->load->view('footer');
            }				
		}

		public function deconnexion(){
            if($this->session->logged){
                $this->session->sess_destroy();               
            }
			redirect("site");
		}
    }
    ?>
