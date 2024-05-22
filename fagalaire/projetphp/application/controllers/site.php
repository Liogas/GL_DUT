<?php 
class Site extends CI_Controller {
    public function index(){
        if($this->session->logged){
            redirect('utilisateur/log');
        } else {
            $this->visiteur();
        }
    }

    public function visiteur(){
        $this->load->helper('form');
        $this->load->view('head');
        $this->load->view('header');
        $this->load->view('Accueil/accueilVisite');
        $this->load->view('Accueil/Inscription');
        $this->load->view('Accueil/Connexion');
        $this->load->view('objetFormulaire/endDiv');
        $this->load->view('objetFormulaire/endDiv');
        $this->load->view('footer');
    }
}
?>
