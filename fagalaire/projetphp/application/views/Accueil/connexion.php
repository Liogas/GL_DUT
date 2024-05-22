<div class="formConnect">
    <section>
        <?php echo form_open('utilisateur/connexion',array()); ?>
        <fieldset>
            <legend>Se connecter</legend>

            <label>Login</label><br>
            <input value="<?=set_value('login')?>" type="textfield" name="login" placeholder="Entrez votre nom d'utilisateur.." required><br>

            <label>Mot de passe</label><br>
            <input value="<?=set_value('password')?>" type="password" name="password" placeholder="Entrez votre mot de passe.." required><br><br>

            <div class="submit">
                <input type="submit" name="valider" value="Connexion" id="valider">
                <input type="reset" value="reset" id="reset">
            </div>
        </fieldset>
    </form>
    </section>
</div>  