import {writable, derived} from 'svelte/store';

import type Keycloak from 'keycloak-js';

export const keycloak = writable<Keycloak | null>(null);

export const isAuthenticated = writable(false);

export const tokenParsed = writable<any>(null);

export const roles = derived(tokenParsed, ($t) => $t?.realm_access?.roles ?? []);

export const isStaff = derived(roles, ($r) => $r.includes('staff'));
export const isUser = derived(roles, ($r) => $r.includes('user'));


export const username = derived(tokenParsed, ($t) => $t?.preferred_username ?? '');