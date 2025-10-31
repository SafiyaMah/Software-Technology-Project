<script>
	import favicon from '$lib/assets/favicon.svg';

	let { children } = $props();
</script>

<svelte:head>
	<link rel="icon" href={favicon} />
</svelte:head>

<nav style="display:flex;gap:1rem;padding:1rem;border-bottom:1px solid #eee;">
    <a href="/">Home</a>
    <a href="/admin">Admin</a>
</nav>

{@render children?.()}
