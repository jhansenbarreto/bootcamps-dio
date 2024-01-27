enum class Nivel {
    BASICO,
    INTERMEDIARIO,
    AVANCADO
}

data class Usuario(val nome: String) {
    override fun toString(): String {
        return nome
    }
}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel) {
    override fun toString(): String {
        val horas = (duracao / 60)
        return "$nome, $nivel, $horas h"
    }
}

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        
        println()
        println("# $nome:")
        println("Usuario $usuario matriculado!")
    }

    fun imprimir() {
        val quantidade = inscritos.size

        println()
        println("# $nome:")
        println(" -> $quantidade inscritos")
        println("# Conteudos:")
        conteudos.forEach({
            print(" -> ")
            println(it.toString())
        })
    }
}

fun main() {
    val conteudos1 = mutableListOf<ConteudoEducacional>()
    
    conteudos1.add(ConteudoEducacional("Docker Compose", nivel = Nivel.AVANCADO))
    conteudos1.add(ConteudoEducacional("Fundamentos Kotlin", 120, Nivel.BASICO))
    conteudos1.add(ConteudoEducacional("API com Kotlin e Spring", 300, Nivel.INTERMEDIARIO))

    val tqiKotlin = Formacao("Bootcamp TQI Kotlin", conteudos1)
    tqiKotlin.matricular(Usuario("Peter Parker"))
    tqiKotlin.matricular(Usuario("Tony Stark"))
    tqiKotlin.matricular(Usuario("Steve Rogers"))
    tqiKotlin.imprimir()

    val conteudos2 = mutableListOf<ConteudoEducacional>()

    conteudos2.add(ConteudoEducacional("Estruturas de Dados", 120, Nivel.BASICO))
    conteudos2.add(ConteudoEducacional("API com Java e Spring", 240, Nivel.INTERMEDIARIO))

    val bancoPanJava = Formacao("Bootcamp Java Developer", conteudos2)
    bancoPanJava.matricular(Usuario("Bruce Wayne"))
    bancoPanJava.matricular(Usuario("Clark Kent"))
    bancoPanJava.matricular(Usuario("Barry Allen"))
    bancoPanJava.imprimir()
}
