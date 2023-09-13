package br.com.fiap.trashit

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.fiap.trashit.model.Endereco
import br.com.fiap.trashit.model.Lixeira
import br.com.fiap.trashit.service.database.repository.EnderecoRepository
import br.com.fiap.trashit.ui.theme.TrashItTheme
import br.com.fiap.trashit.view.ColetasScreen
import br.com.fiap.trashit.view.LixeiraScreen
import br.com.fiap.trashit.view.LoginScreen
import br.com.fiap.trashit.view.PerfilScreen
import br.com.fiap.trashit.view.navbar.BottomNavItem
import br.com.fiap.trashit.view.navbar.BottomNavigation
import br.com.fiap.trashit.viewmodel.LixeiraViewModel
import br.com.fiap.trashit.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val enderecoRepository = EnderecoRepository(this)

        if (enderecoRepository.listarEnderecos().isEmpty()) {
            val end1 = Endereco(
                0, "09210310", "125", "Rua Exemplo", "",
                "Bairro Exemplo", "Cidade Exemplo", "Estado Exemplo", Lixeira()
            )
            enderecoRepository.salvar(end1)
        }



        /* var end2 = Endereco(1,"09210310","125 Atualizada","Rua Atualizada", "",
            "Bairro Atualizada", "Cidade Atualizada", "Estado Atualizada", Lixeira()
        )*/




        Log.e("ENDERECO", enderecoRepository.listarEnderecos().toString())
        /* Log.e("ENDERECO", enderecoRepository.atualizar(end2).toString())
        Log.e("ENDERECO", enderecoRepository.listarEnderecos().toString())
        Log.e("ENDERECO", enderecoRepository.excluir(end2).toString())
        Log.e("ENDERECO", enderecoRepository.listarEnderecos().toString())
*/
        setContent {
            TrashItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var showBottomBar by rememberSaveable {
                        mutableStateOf(false)
                    }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    showBottomBar = when (navBackStackEntry?.destination?.route) {
                        "login" -> false
                        else -> true
                    }


                    Scaffold(
                        bottomBar = { if(showBottomBar) BottomNavigation(navController = navController) }) {
                        it -> Log.d("PADDING", "$it")
                        NavHost(navController = navController, startDestination = "login" ) {
                            composable(route = "login"){LoginScreen(
                                viewModel = LoginViewModel(),
                                navController = navController
                            )}
                            composable(route = BottomNavItem.Lixeira.screenRoute){ LixeiraScreen(
                                viewModel = LixeiraViewModel(context = applicationContext),
                                navController = navController
                            )}
                            composable(route = BottomNavItem.Coletas.screenRoute){
                                ColetasScreen(navController = navController)
                            }
                            composable(route = BottomNavItem.Conta.screenRoute){
                                PerfilScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
