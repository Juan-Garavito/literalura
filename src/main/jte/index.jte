@import com.reto.literalura.model.Libro
@import java.util.List;

@param List<Libro> libros

<!doctype html>
<html lang="es">
<head>
    <title>Literalura</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="title" content="Literalura">
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<header class="bg-blue-600 text-white p-4 shadow-md">
    <div class="container mx-auto flex justify-between items-center">
        <h1 class="text-2xl font-bold">Literalura</h1>
        <div class="relative">
            <select id="filtro" class="appearance-none bg-white border border-gray-300 text-gray-700 py-2 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
                <option value="all">Todos los idiomas</option>
                <option value="es">Español</option>
                <option value="en">Inglés</option>
            </select>
            <div class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700">
                <svg class="fill-current h-4 w-4" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
                    <path d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"/>
                </svg>
            </div>
        </div>
        <nav>
            <a href="/" class="mx-2 hover:underline">Libros</a>
            <a href="autores" class="mx-2 hover:underline">Autores</a>
        </nav>
    </div>
</header>
<main class="container mx-auto mt-8 px-4">
    <h2 class="text-3xl font-bold mb-6 text-center text-gray-800">Lista de libros</h2>
    <div id="libros-grid" class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6">
        @for(Libro libro : libros)
            <div class="libro bg-white rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105 flex flex-col" data-lenguajes="${libro.getLenguajes()}">
                <div class="relative pt-[150%]">
                    <img src="${libro.getPoster()}" alt="${libro.getTitulo()}" class="absolute top-0 left-0 w-full h-full object-cover">
                </div>
                <div class="p-4 flex-grow">
                    <h3 class="font-bold text-xl mb-2 text-gray-800">${libro.getTitulo()}</h3>
                    <p class="text-gray-600 mb-2">Lenguajes: ${libro.getLenguajes()}</p>
                    <p class="text-gray-600">Descargas: ${libro.getDescargas()}</p>
                </div>
            </div>
        @endfor
    </div>
</main>

@raw
<script>
    const $filtro = document.getElementById('filtro');
    const $librosGrid = document.getElementById('libros-grid');


    $filtro.addEventListener('change', function() {
        const selectedLanguage = this.value;
        fetch("/lenguaje/"+selectedLanguage).then(res=>res.json()).then(res=>{
            $librosGrid.innerHTML = ""
            res.forEach(libro=>{
                $librosGrid.innerHTML += `
                <div class="libro bg-white rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105 flex flex-col" data-lenguajes="${libro.lenguajes}">
                    <div class="relative pt-[150%]">
                        <img src="${libro.poster}" alt="${libro.titulo}" class="absolute top-0 left-0 w-full h-full object-cover">
                    </div>
                    <div class="p-4 flex-grow">
                        <h3 class="font-bold text-xl mb-2 text-gray-800">${libro.titulo}</h3>
                        <p class="text-gray-600 mb-2">Lenguajes: ${libro.lenguajes}</p>
                        <p class="text-gray-600">Descargas: ${libro.descargas}</p>
                    </div>
                </div>`
            })

        })

    });
</script>
@endraw
</body>
</html>