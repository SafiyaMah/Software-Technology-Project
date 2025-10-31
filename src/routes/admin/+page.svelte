<script>
    const cards = [
        { title: 'Create poll', href: '/admin/polls/new', desc: 'Make a new poll' },
        { title: 'View results', href: '/admin/polls', desc: 'See results for poll' },
        { title: 'Users', href: '/admin/users', desc: 'View user info' }
    ];
</script>

<section style="max-width: 960px; margin: 2rem auto; padding: 1rem;">
    <h1>Admin Dashboard</h1>
    <div style="display:grid;grid-template-columns:repeat(auto-fit,minmax(240px,1fr));gap:1rem;margin-top:1rem;">
        {#each cards as c}
            <a href={c.href} style="display:block;border:1px solid #ddd;border-radius:12px;padding:1rem;text-decoration:none;">
                <h2 style="margin:0 0 .25rem 0;">{c.title}</h2>
                <p style="margin:0;color:#555;">{c.desc}</p>
            </a>
        {/each}
    </div>
</section>
