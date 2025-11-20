import adapter from '@sveltejs/adapter-static';

const config = {
    kit: {
        adapter: adapter({ fallback: 'index.html' }),
        // SPA build (don’t prerender pages)
        prerender: { entries: [] }
    }
};

export default config;