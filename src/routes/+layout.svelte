<script lang="ts">
	import favicon from '$lib/assets/favicon.svg';
    import { onMount } from 'svelte';
    import { keycloak } from '$lib/keycloak';

    let ready = false;

    onMount(async () => {
        await keycloak.init({
            onLoad: 'login-required',
            checkLoginIframe: false,
            pkceMethod: 'S256'
        });

        ready = true;
    });

    function logout(){
        keycloak.logout({
            redirectUri: 'http://localhost:5173'
        });
    }

	let { children } = $props();
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
</svelte:head>

<nav style="display:flex;gap:1rem;padding:1rem;border-bottom:1px solid #eee;">
    <a href="/">Home</a>
    <a href="/admin">Admin</a>
    <button onclick={logout}>logout</button>
</nav>

{@render children?.()}
