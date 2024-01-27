var modal = document.getElementById("details")
modal.addEventListener('click', function (e) {
    if (e.target == this) {
        closeModal()
    }
});

function openModal(id) {
    pokemon = pokeList[id - 1]
    document.getElementById("modalTitle").innerHTML = `
        <button type="button" class="btnBack" onclick="closeModal()">
            <
        </button>
        <h1>${pokemon.name}</h1>
        <h3>#${pokemon.number}</h3>
    `
    let img = document.getElementById("pokeImg")
    img.setAttribute('src', `${pokemon.photo}`)
    img.setAttribute('alt', `${pokemon.name}`)

    document.getElementById("modalContent").innerHTML = `
        <ol class="types">
            ${pokemon.types.map((type) => `<li class="type ${type}">${type}</li>`).join('')}
        </ol>

        <p>Peso: ${pokemon.weight / 10} kg | Altura: ${pokemon.height / 10} m</p>

        <div class="stats">
            <p>Estatísticas</p>
            <table>
                ${pokemon.stats.map((stat) => `
                    <tr>
                        <td class="esquerda">${stat.name}</td>
                        <td class="centro">${stat.value}</td>
                        <td class="direita"><progress value="${stat.value}" max="250" style="--value: ${stat.value}; --max: 250;"/></td>
                    </tr>`)
            .join('')}
            </table>
        </div>
    `
    modal.style.display = "block"
    document.body.style.overflow = "hidden"
}

function closeModal() {
    modal.style.display = "none"
    document.body.style.overflow = "auto"
}