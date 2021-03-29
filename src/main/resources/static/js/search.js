const allCocktails = document.getElementById('albumsList')
const searchBar = document.getElementById('searchInput')
const searchBtn = document.getElementById('searchBtn')
const h2 = document.getElementById('h2')

const cocktailList = [];

fetch("http://localhost:8080/cocktails/api").
then(response => response.json()).
then(data => {
    for (let cocktail of data) {
        cocktailList.push(cocktail);
    }
})

searchBtn.addEventListener('click', (e) => {
    const searchingChars = searchBar.value.toLowerCase();
    let filterCocktails = cocktailList.filter(cocktail => {
        return cocktail.name.toLowerCase().includes(searchingChars);
    });

    filterCocktails.length ? displayAlbums(filterCocktails) : displayNoCocktailFound()
})

const displayNoCocktailFound = (cocktails) => {
    h2.innerText = 'No such Cocktails.Try again.'
    allCocktails.innerText = ''
}

const displayAlbums = (cocktails) => {
    h2.innerText = 'Here can search.'
    allCocktails.innerHTML = cocktails
        .map((c) => {
            return `<div class="row no-collapse-1">
                        <section class="4u">
                            <div class="item">
                                <a href="/cocktails/${c.id}" class="image feature">
                                    <img class="img" src="${c.imgUrl}"
                                        alt="imgUrl"></a>
                                <span>${c.baseAlcohol}</span>
                                <p>${c.name}</p>
                            </div>
                        </section>
                    </div>`
        })
        .join('');
}