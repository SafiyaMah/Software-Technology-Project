// import { sveltekit } from '@sveltejs/kit/vite';
// import { defineConfig } from 'vite';

// export default defineConfig({
//     plugins: [sveltekit()],
//     server: {
//         proxy: {
//             '/api': {
//                 target: 'http://localhost:8080',
//                 changeOrigin: true, 
//                 secure: false, 
// 				// rewrite: (path) => path.replace(/^\/api/, ''),
//             },
//             '/realms': { 
//                 target: 'http://localhost:8080',
//                 changeOrigin: true,
//                 secure: false,
//             }
//         }
//     }
// });


import { sveltekit } from '@sveltejs/kit/vite';
import { defineConfig } from 'vite';


export default defineConfig({
	server: {
		proxy: {
			'/api': 'http://localhost:8080'
		}
	},
	plugins: [sveltekit()]
});