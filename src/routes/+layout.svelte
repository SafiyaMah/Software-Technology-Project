<script lang="ts">
    export const ssr = false;

    import favicon from '$lib/assets/favicon.svg';
    import { onMount } from 'svelte';
    import { keycloak } from '$lib/keycloak';
    import { isAuthenticated, tokenParsed } from '$lib/authStore';

    let ready = false;

    onMount(async () => {
        try {
            const auth = await keycloak.init({
                onLoad: 'login-required',
                checkLoginIframe: false,
                pkceMethod: 'S256'
            });
            isAuthenticated.set(auth);
            tokenParsed.set(keycloak.tokenParsed ?? null);
        } finally {
            ready = true;
        }
    });

    function logout() {
        isAuthenticated.set(false);
        tokenParsed.set(null);
        keycloak.logout({
            redirectUri: window.location.origin // http://localhost:5173
        });
    }
</script>

<svelte:head>
    <link rel="icon" href="{favicon}" />
</svelte:head>

{#if !ready}
    <p>Loading authentication...</p>
<!-- {:else}
    <nav style="display:flex; gap:1rem; padding:1rem; border-bottom:1px solid #eee;">
        <a href="/">Home</a>
        <a href="/admin">Admin</a>
        <button on:click="{logout}">Logout</button>
    </nav>

    <slot />
{/if} -->
{:else}
    <div class="app-shell">
        <header class="topbar">

            <nav class="nav">
                <a href="/">Home</a>
                <a href="/poll">Poll</a>
                <a href="/admin">Admin</a>
            </nav>

            <button class="btn btn-ghost" on:click={logout}>Logout</button>
        </header>

        <main class="content">
            <slot />
        </main>

        <footer class="footer">
            <span>© {new Date().getFullYear()} DocPoll</span>
        </footer>
    </div>
{/if}

<style>
    :global(body) {
        margin: 0;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI',
            sans-serif;
        background: #A3D9A5;
        color: #111827;
    }

    .app-shell {
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }

    .topbar {
    display: flex;
    align-items: center;
    justify-content: center;    
    padding: 1rem 1.5rem;      
    background: #DFF3DF;
    border-bottom: 1px solid #e5e7eb;
    position: sticky;
    top: 0;
    z-index: 10;
    height: 70px;               
}


    .logo {
        font-weight: 600;
        letter-spacing: 0.03em;
        font-size: 1.1rem;
        color: #3B4A54;
    }

    .nav {
        display: flex;
        gap: 1rem;
    }

    .nav a {
        text-decoration: none;
        color: #2F3A45;
        font-size: 1.1rem;
        padding: 0.5rem 1rem;
        border-radius: 12px;
        transition: background 0.15s ease, color 0.15s ease;
    }

    .nav a:hover {
        background: #A3D9A5;
        color: #ffffff;
		box-shadow: 0 10px 20px rgba(123, 196, 127, 0.3);
    }
	.content {
		flex: 1;
		width: 100%;
		max-width: none; 
		margin: 2rem 0;
		padding: 2rem;
	}
	
    .footer {
        padding: 0.75rem 1.5rem;
        font-size: 0.8rem;
        color: #6b7280;
        text-align: center;
    }

    .btn {
        border: none;
        border-radius: 999px;
        padding: 0.4rem 0.9rem;
        font-size: 0.9rem;
        cursor: pointer;
        transition: background 0.15s ease, color 0.15s ease,
            box-shadow 0.15s ease;
    }

    .btn-ghost {
        background: transparent;
        color: #4b5563;
    }

    .btn-ghost:hover {
        background: #f3f4f6;
    }

    .loading {
        margin: 0 auto;
        padding-top: 4rem;
        text-align: center;
        color: #6b7280;
    }
</style>