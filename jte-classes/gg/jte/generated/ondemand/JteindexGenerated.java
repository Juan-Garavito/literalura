package gg.jte.generated.ondemand;
import com.reto.literalura.model.Libro;
import java.util.List;;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,39,39,39,40,40,40,40,40,40,40,40,40,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,45,45,45,46,46,46,47,47,47,50,50,54,82,84,84,84,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<Libro> libros) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"es\">\n<head>\n    <title>Literalura</title>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <meta name=\"title\" content=\"Literalura\">\n    <script src=\"https://cdn.tailwindcss.com\"></script>\n</head>\n<body class=\"bg-gray-100\">\n<header class=\"bg-blue-600 text-white p-4 shadow-md\">\n    <div class=\"container mx-auto flex justify-between items-center\">\n        <h1 class=\"text-2xl font-bold\">Literalura</h1>\n        <div class=\"relative\">\n            <select id=\"filtro\" class=\"appearance-none bg-white border border-gray-300 text-gray-700 py-2 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500\">\n                <option value=\"all\">Todos los idiomas</option>\n                <option value=\"es\">Español</option>\n                <option value=\"en\">Inglés</option>\n            </select>\n            <div class=\"pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700\">\n                <svg class=\"fill-current h-4 w-4\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\">\n                    <path d=\"M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z\"/>\n                </svg>\n            </div>\n        </div>\n        <nav>\n            <a href=\"/\" class=\"mx-2 hover:underline\">Libros</a>\n            <a href=\"autores\" class=\"mx-2 hover:underline\">Autores</a>\n        </nav>\n    </div>\n</header>\n<main class=\"container mx-auto mt-8 px-4\">\n    <h2 class=\"text-3xl font-bold mb-6 text-center text-gray-800\">Lista de libros</h2>\n    <div id=\"libros-grid\" class=\"grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6\">\n        ");
		for (Libro libro : libros) {
			jteOutput.writeContent("\n            <div class=\"libro bg-white rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105 flex flex-col\"");
			var __jte_html_attribute_0 = libro.getLenguajes();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" data-lenguajes=\"");
				jteOutput.setContext("div", "data-lenguajes");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("div", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">\n                <div class=\"relative pt-[150%]\">\n                    <img");
			var __jte_html_attribute_1 = libro.getPoster();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
				jteOutput.writeContent(" src=\"");
				jteOutput.setContext("img", "src");
				jteOutput.writeUserContent(__jte_html_attribute_1);
				jteOutput.setContext("img", null);
				jteOutput.writeContent("\"");
			}
			var __jte_html_attribute_2 = libro.getTitulo();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
				jteOutput.writeContent(" alt=\"");
				jteOutput.setContext("img", "alt");
				jteOutput.writeUserContent(__jte_html_attribute_2);
				jteOutput.setContext("img", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(" class=\"absolute top-0 left-0 w-full h-full object-cover\">\n                </div>\n                <div class=\"p-4 flex-grow\">\n                    <h3 class=\"font-bold text-xl mb-2 text-gray-800\">");
			jteOutput.setContext("h3", null);
			jteOutput.writeUserContent(libro.getTitulo());
			jteOutput.writeContent("</h3>\n                    <p class=\"text-gray-600 mb-2\">Lenguajes: ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(libro.getLenguajes());
			jteOutput.writeContent("</p>\n                    <p class=\"text-gray-600\">Descargas: ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(libro.getDescargas());
			jteOutput.writeContent("</p>\n                </div>\n            </div>\n        ");
		}
		jteOutput.writeContent("\n    </div>\n</main>\n\n");
		jteOutput.writeContent("\n<script>\n    const $filtro = document.getElementById('filtro');\n    const $librosGrid = document.getElementById('libros-grid');\n\n\n    $filtro.addEventListener('change', function() {\n        const selectedLanguage = this.value;\n        fetch(\"/lenguaje/\"+selectedLanguage).then(res=>res.json()).then(res=>{\n            $librosGrid.innerHTML = \"\"\n            res.forEach(libro=>{\n                $librosGrid.innerHTML += `\n                <div class=\"libro bg-white rounded-lg shadow-md overflow-hidden transition-transform duration-300 hover:scale-105 flex flex-col\" data-lenguajes=\"${libro.lenguajes}\">\n                    <div class=\"relative pt-[150%]\">\n                        <img src=\"${libro.poster}\" alt=\"${libro.titulo}\" class=\"absolute top-0 left-0 w-full h-full object-cover\">\n                    </div>\n                    <div class=\"p-4 flex-grow\">\n                        <h3 class=\"font-bold text-xl mb-2 text-gray-800\">${libro.titulo}</h3>\n                        <p class=\"text-gray-600 mb-2\">Lenguajes: ${libro.lenguajes}</p>\n                        <p class=\"text-gray-600\">Descargas: ${libro.descargas}</p>\n                    </div>\n                </div>`\n            })\n\n        })\n\n    });\n</script>\n");
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<Libro> libros = (List<Libro>)params.get("libros");
		render(jteOutput, jteHtmlInterceptor, libros);
	}
}
